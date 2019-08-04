package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

	@FXML
	private GridPane squares;

	private List<ImageView> initialEntities;

	private List<ImageView> deletedEntities;

	private Player player;

	private ImageView playerImageView;

	private Map<String, Image> images;

	private Dungeon dungeon;

	DungeonControllerLoader loader;

	private static final long THRESHOLD = 10_000_000_000L;

	private long lastMoveNanos;

	/**
	 * constructor for dungeon controller
	 * 
	 * @param dungeon
	 * @param initialEntities
	 * @param images
	 */
	public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, Map<String, Image> images) {
		this.dungeon = dungeon;
		this.player = dungeon.getPlayer();
		this.initialEntities = new ArrayList<>(initialEntities);
		this.deletedEntities = new ArrayList<>();
		this.images = images;

	}

	/**
	 * listens to player's isAlive attribute
	 * 
	 * @param dungeonScreen to end game
	 */
	public void listenPlayerStatus(DungeonScreen dungeonScreen) {
		getPlayerStatus().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {
				dungeonScreen.gameOver();
			}
		});
	}

	/**
	 * listens to dungeon's hasWin status
	 * 
	 * @param dungeonScreen to end game
	 */
	public void listenWinStatus(DungeonScreen dungeonScreen) {
		getWinStatus().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				dungeonScreen.gameWon();
			} else {
			}
		});
	}

	@FXML
	public void initialize() {
		Image ground = new Image("/dirt_0_new.png");

		// Add the ground first so it is below all other entities
		for (int x = 0; x < dungeon.getWidth(); x++) {
			for (int y = 0; y < dungeon.getHeight(); y++) {
				squares.add(new ImageView(ground), x, y);
			}
		}

		for (ImageView entity : initialEntities) {
			if (entity.getImage() != images.get("Player")) {
				squares.getChildren().add(entity);
			}
		}

		for (ImageView entity : initialEntities) {
			if (entity.getImage() == images.get("Player")) {
				playerImageView = entity;
				squares.getChildren().add(entity);
			}
		}
	}

	/**
	 * handles player's request to look at backpack
	 * 
	 * @return mapping of item names to amounts
	 */
	public Map<String, Integer> handleSeeBackpackRequest() {
		return dungeon.getItemsInBackpack();
	}

	@FXML
	public void handleKeyPress(KeyEvent event) {

		long now = System.nanoTime();
		if (event.getCode().isArrowKey()) {
			event.consume();

			if (lastMoveNanos <= 0L || now - lastMoveNanos >= (THRESHOLD / player.getSpeedFactor())) {

				switch (event.getCode()) {
				case UP:
					player.moveUp();
					break;
				case DOWN:
					player.moveDown();
					break;
				case LEFT:
					player.moveLeft();
					break;
				case RIGHT:
					player.moveRight();
					break;
				default:
					break;
				}
				dungeon.notifyPlayerObservers();
				lastMoveNanos = now;
			}
		} else if (event.getCode().isWhitespaceKey()) {
			if (player.equipItem()) {
				removeNodeByRowColumnIndex(player.getX(), player.getY(), squares);
				dungeon.removeEquippedEntityFromDungeon(player.getX(), player.getY());
			}
		} else if (event.getCode() == KeyCode.G) {
			player.useItem("Potion");
		} else if (event.getCode() == KeyCode.F) {
			if (player.countSwordInBackPack() != 0 && !(dungeon.hasEquipable(player.getX(), player.getY()))) {
				addNodeByRowColumnIndex(player.getX(), player.getY(), squares, (Image) images.get("Sword"));
				dungeon.removeEquippedEntityFromBackPack(player.getX(), player.getY(), "Sword");
			}
		} else if (event.getCode() == KeyCode.T) {
			Bomb bomb = player.getBomb();
			if (bomb != null && !dungeon.hasBomb(player.getX(), player.getY())) {
				dungeon.addEntity(bomb);
				bomb.setX(player.getX());
				bomb.setY(player.getY());
				bomb.light();
				addNodeByRowColumnIndex(player.getX(), player.getY(), squares, (Image) images.get("Bomb"));
				// remove bomb from player's backpack.
				player.useItem("Bomb");
			}
		} else if (event.getCode() == KeyCode.V) {
			if (player.findKey() != null && !(dungeon.hasEquipable(player.getX(), player.getY()))) {
				addNodeByRowColumnIndex(player.getX(), player.getY(), squares, (Image) images.get("Key"));
				dungeon.removeEquippedEntityFromBackPack(player.getX(), player.getY(), "Key");
			}
		}
	}

	/**
	 * removes an item by its column and row
	 * 
	 * @param column   of item
	 * @param row      of item
	 * @param gridPane containing items
	 * @return true if removed successfully
	 */
	public boolean removeNodeByRowColumnIndex(int column, int row, GridPane gridPane) {

		for (Node node : initialEntities) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				ImageView imageView = (ImageView) node;
				ImageView possibleBomb = getExplodedBomb(column, row, gridPane);
				if (possibleBomb != null) {
					deletedEntities.add(possibleBomb);
					initialEntities.remove(possibleBomb);
					gridPane.getChildren().remove(possibleBomb);
					return true;
				}
				if (imageView.getImage() != (Image) images.get("Player")
						&& imageView.getImage() != (Image) images.get("Hound")
						&& imageView.getImage() != (Image) images.get("Mage")) {
					deletedEntities.add(imageView);
					initialEntities.remove(imageView);
					gridPane.getChildren().remove(imageView);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * adds a node to a given row and column
	 * 
	 * @param column   to put item
	 * @param row      to put item
	 * @param gridPane to put item
	 * @param image    to put
	 * @return true if put successfully
	 */
	public boolean addNodeByRowColumnIndex(int column, int row, GridPane gridPane, Image image) {
		for (ImageView imageView : deletedEntities) {
			if (imageView.getImage() == image) {
				Node node = (Node) imageView;
				GridPane.setColumnIndex(node, player.getX());
				GridPane.setRowIndex(node, player.getY());
				initialEntities.add(imageView);
				deletedEntities.remove(imageView);
				gridPane.getChildren().add(node);
				playerImageView.toFront();
				return true;
			}
		}
		return false;
	}

	/**
	 * gets mapping of items to amounts
	 * 
	 * @return
	 */
	public Map<String, Image> getImages() {
		return this.images;
	}

	/**
	 * pauses the game
	 */
	public void pauseGame() {
		dungeon.pauseGame();
	}

	/**
	 * gets the isAlive attribute from player
	 * 
	 * @return
	 */
	public BooleanProperty getPlayerStatus() {
		return player.isAlive();
	}

	/**
	 * gets the hasWin from dungeon
	 * 
	 * @return
	 */
	public BooleanProperty getWinStatus() {
		return dungeon.hasWin();
	}

	/**
	 * resumes the game
	 */
	public void resumeGame() {
		dungeon.resumeGame();
	}

	/**
	 * gets the pane
	 * 
	 * @return
	 */
	public GridPane getPane() {
		return squares;
	}

	/**
	 * gets the image of an exploded bomb if there is one
	 * 
	 * @param column   to look for
	 * @param row      to look for
	 * @param gridPane to go to
	 * @return bomb if found, null otherwise
	 */
	public ImageView getExplodedBomb(int column, int row, GridPane gridPane) {
		for (Node node : initialEntities) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				ImageView imageView = (ImageView) node;
				if (((LocatedImage) imageView.getImage()).getUrl().equals("/bomb_lit_4.png")) {
					return imageView;
				}
			}
		}
		return null;

	}
}
