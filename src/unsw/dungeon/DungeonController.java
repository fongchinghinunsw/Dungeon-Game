package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	private Player player;

	private Image playerImage;

	private Dungeon dungeon;

	private static final long THRESHOLD = 100_000_000L;

	private long lastMoveNanos;

	public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, Image playerImage) {
		this.dungeon = dungeon;
		this.player = dungeon.getPlayer();
		this.initialEntities = new ArrayList<>(initialEntities);
		this.playerImage = playerImage;
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
			if (entity.getImage() != playerImage) {
				squares.getChildren().add(entity);
			}
		}

		for (ImageView entity : initialEntities) {
			if (entity.getImage() == playerImage) {
				squares.getChildren().add(entity);
			}
		}

	}

	@FXML
	public void handleKeyPress(KeyEvent event) {

		long now = System.nanoTime();
		if (event.getCode().isArrowKey()) {
			event.consume();
			if (lastMoveNanos <= 0L || now - lastMoveNanos >= THRESHOLD) {

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
				player.notifyObservers();
				lastMoveNanos = now;
			}
		} else if (event.getCode().isWhitespaceKey()) {
			if (player.equipItem()) {
				removeNodeByRowColumnIndex(player.getX(), player.getY(), squares);
			}
		}
	}

	public boolean removeNodeByRowColumnIndex(int column, int row, GridPane gridPane) {

		for (Node node : initialEntities) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				ImageView imageView = (ImageView) node;
				if (imageView.getImage() != playerImage) {
					gridPane.getChildren().remove(imageView);
					return true;
				}

			}
		}
		return false;
	}

}
