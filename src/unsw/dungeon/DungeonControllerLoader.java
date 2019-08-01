package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

	private List<ImageView> entities;

	// Images
	private Image playerImage;
	private Image wallImage;
	private Image exitImage;
	private Image boulderImage;
	private Image keyImage;
	private Image treasureImage;
	private Image potionImage;
	private Image bombImage;
	private Image bombImageLit1;
	private Image bombImageLit2;
	private Image bombImageLit3;
	private Image enemyImage;
	private Image swordImage;
	private Image switchImage;
	private Image closedDoorImage;
	private Image openDoorImage;

	public DungeonControllerLoader(String filename) throws FileNotFoundException {
		super(filename);
		entities = new ArrayList<>();
		playerImage = new Image("/human_new.png");
		wallImage = new Image("/brick_brown_0.png");
		exitImage = new Image("/exit.png");
		boulderImage = new Image("/boulder.png");
		keyImage = new Image("/key.png");
		treasureImage = new Image("/gold_pile.png");
		potionImage = new Image("/brilliant_blue_new.png");
		bombImage = new Image("/bomb_unlit.png");
		bombImageLit1 = new Image("/bomb_lit_2.png");
		bombImageLit2 = new Image("/bomb_lit_3.png");
		bombImageLit3 = new Image("/bomb_lit_4.png");
		enemyImage = new Image("/hound.png");
		swordImage = new Image("/greatsword_1_new.png");
		switchImage = new Image("/pressure_plate.png");
		closedDoorImage = new Image("/closed_door.png");
		openDoorImage = new Image("/open_door.png");
	}

	@Override
	public void onLoad(Entity player) {
		ImageView view = new ImageView(playerImage);
		addEntity(player, view);
	}

	@Override
	public void onLoad(Wall wall) {
		ImageView view = new ImageView(wallImage);
		addEntity(wall, view);
	}

	@Override
	public void onLoad(Exit exit) {
		ImageView view = new ImageView(exitImage);
		addEntity(exit, view);
	}

	@Override
	public void onLoad(Boulder boulder) {
		ImageView view = new ImageView(boulderImage);
		addEntity(boulder, view);
	}

	@Override
	public void onLoad(Key key) {
		ImageView view = new ImageView(keyImage);
		addEntity(key, view);
	}

	@Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
		addEntity(treasure, view);
	}

	public void onLoad(Potion potion) {
		ImageView view = new ImageView(potionImage);
		addEntity(potion, view);
	}

	@Override
	public void onLoad(Bomb bomb) {
		ImageView view = new ImageView(bombImage);
		addEntity(bomb, view);
	}

	@Override
	public void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyImage);
		addEntity(enemy, view);
	}

	@Override
	public void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);
		addEntity(sword, view);
	}

	@Override
	public void onLoad(Switch floorSwitch) {
		ImageView view = new ImageView(switchImage);
		addEntity(floorSwitch, view);
	}

	@Override
	public void onLoad(Door door) {
		ImageView view = new ImageView(closedDoorImage);
		addEntity(door, view);
	}

	private void addEntity(Entity entity, ImageView view) {
		trackPosition(entity, view);

		entities.add(view);
	}

	/**
	 * Set a node in a GridPane to have its position track the position of an entity
	 * in the dungeon.
	 *
	 * By connecting the model with the view in this way, the model requires no
	 * knowledge of the view and changes to the position of entities in the model
	 * will automatically be reflected in the view.
	 * 
	 * @param entity
	 * @param node
	 */
	private void trackPosition(Entity entity, Node node) {
		GridPane.setColumnIndex(node, entity.getX());
		GridPane.setRowIndex(node, entity.getY());
		entity.x().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				GridPane.setColumnIndex(node, newValue.intValue());
			}
		});
		entity.y().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				GridPane.setRowIndex(node, newValue.intValue());
			}
		});
		if (entity.getClassName().equals("Player")) {
			Player player = (Player) entity;
			player.isAlive().addListener((observable, oldValue, newValue) -> {
				if (!newValue) {
					// currently just move the image to the back of the pane.
					node.toBack();
				}
			});

		}
		if (entity.getClassName().equals("Enemy")) {
			Enemy enemy = (Enemy) entity;
			enemy.isAlive().addListener((observable, oldValue, newValue) -> {
				if (!newValue) {
					// currently just move the image to the back of the pane.
					node.toBack();
				}
			});

		}
		if (entity.getClassName().equals("Boulder")) {
			Boulder boulder = (Boulder) entity;
			boulder.destroyed().addListener((observable, oldValue, newValue) -> {
				if (newValue) {
					node.toBack();
				}
			});
		}

		if (entity.getClassName().equals("Bomb")) {
			Bomb bomb = (Bomb) entity;
			bomb.getTime().addListener((observable, oldValue, newValue) -> {
				ImageView img = (ImageView) node;
				if (newValue.intValue() == 3) {
					img.setImage(bombImageLit1);
				} else if (newValue.intValue() == 2) {
					img.setImage(bombImageLit2);
				} else if (newValue.intValue() == 1) {
					img.setImage(bombImageLit3);
					bomb.notifyObservers();
				} else if (newValue.intValue() == 0) {
					img.toBack();
				}
			});
		}

		if (entity.getClassName().equals("Potion")) {
			Potion potion = (Potion) entity;
			potion.getTime().addListener((observable, oldValue, newValue) -> {
				System.out.println(newValue);
				if (newValue.intValue() == 0) {
					potion.disablePotion();
				}
			});
		}

		if (entity.getClassName().equals("Door")) {
			Door door = (Door) entity;
			door.getOpen().addListener((observable, oldValue, newValue) -> {
				if (newValue) {
					ImageView img = (ImageView) node;
					img.setImage(openDoorImage);
				}
			});
		}
	}

	/**
	 * Create a controller that can be attached to the DungeonView with all the
	 * loaded entities.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public DungeonController loadController() throws FileNotFoundException {
		Map<String, Image> map = new HashMap<String, Image>();
		map.put("Player", playerImage);
		map.put("Sword", swordImage);
		map.put("Enemy", enemyImage);
		map.put("Bomb", bombImage);
		map.put("Key", keyImage);
		map.put("Treasure", treasureImage);
		map.put("Potion", potionImage);
		return new DungeonController(load(), entities, map);
	}

}
