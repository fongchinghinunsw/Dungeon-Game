package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class TutorialScreen {
	private Stage stage;
	private String title;
	private TutorialScreenController controller;
	private Scene scene;

	public TutorialScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Tutorial";

		controller = new TutorialScreenController(stage);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Tutorial.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		GridPane root = loader.load();
		root.setPrefSize(750, 400);
		// ImageView background = new ImageView(new Image("/EpicGames.png"));
		// root.add(background, 0, 0);
		for (int i = 0; i < 100; i++) {
			RowConstraints row = new RowConstraints(5);
			root.getRowConstraints().add(row);
		}
		for (int i = 0; i < 150; i++) {
			ColumnConstraints column = new ColumnConstraints(5);
			root.getColumnConstraints().add(column);
		}
		ImageView player = new ImageView(new Image("/human_new.png"));
		root.add(player, 2, 6);
		ImageView bomb = new ImageView(new Image("/bomb_unlit.png"));
		root.add(bomb, 2, 18);
		ImageView potion = new ImageView(new Image("/brilliant_blue_new.png"));
		root.add(potion, 2, 30);
		ImageView sword = new ImageView(new Image("/greatsword_1_new.png"));
		root.add(sword, 2, 42);
		ImageView key = new ImageView(new Image("/key.png"));
		root.add(key, 2, 54);
		ImageView treasure = new ImageView(new Image("/gold_pile.png"));
		root.add(treasure, 2, 66);
		ImageView wall = new ImageView(new Image("/brick_brown_0.png"));
		root.add(wall, 70, 6);
		ImageView boulder = new ImageView(new Image("/boulder.png"));
		root.add(boulder, 70, 18);
		ImageView enemy = new ImageView(new Image("/hound.png"));
		root.add(enemy, 70, 30);
		ImageView door = new ImageView(new Image("/closed_door.png"));
		root.add(door, 70, 42);
		ImageView trigger = new ImageView(new Image("/pressure_plate.png"));
		root.add(trigger, 70, 54);
		ImageView exit = new ImageView(new Image("/exit.png"));
		root.add(exit, 70, 66);
		Label playerLabel = new Label("This is YOU. Press SPACE to pick up item.");
		root.add(playerLabel, 10, 6);
		playerLabel.setMinWidth(Region.USE_PREF_SIZE);
		playerLabel.setMaxWidth(Region.USE_PREF_SIZE);
		playerLabel.setTextFill(Paint.valueOf("White"));
		
		Label playerLabel2 = new Label("Use arrow keys to move yourself around :)");
		root.add(playerLabel2, 10, 10);
		playerLabel2.setMinWidth(Region.USE_PREF_SIZE);
		playerLabel2.setMaxWidth(Region.USE_PREF_SIZE);
		playerLabel2.setTextFill(Paint.valueOf("White"));
		
		Label bombLabel1 = new Label("Press T to use Bomb. Explodes in 3 seconds.");
		root.add(bombLabel1, 10, 18);
		bombLabel1.setMinWidth(Region.USE_PREF_SIZE);
		bombLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		bombLabel1.setTextFill(Paint.valueOf("White"));

		Label bombLabel3 = new Label("Destroys any player, enemy or boulder nearby.");
		root.add(bombLabel3, 10, 22);
		bombLabel3.setMinWidth(Region.USE_PREF_SIZE);
		bombLabel3.setMaxWidth(Region.USE_PREF_SIZE);
		bombLabel3.setTextFill(Paint.valueOf("White"));
		
		
		Label potionLabel1 = new Label("Press G to use Potion. Lasts for 5 seconds.");
		root.add(potionLabel1, 10, 30);
		potionLabel1.setMinWidth(Region.USE_PREF_SIZE);
		potionLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		potionLabel1.setTextFill(Paint.valueOf("White"));
		
		Label potionLabel2 = new Label("Enemy on the same grid with you gets killed");
		root.add(potionLabel2, 10, 34);
		potionLabel2.setMinWidth(Region.USE_PREF_SIZE);
		potionLabel2.setMaxWidth(Region.USE_PREF_SIZE);
		potionLabel2.setTextFill(Paint.valueOf("White"));

		Label swordLabel1 = new Label("Sword lasts 5 hits.");
		root.add(swordLabel1, 10, 42);
		swordLabel1.setMinWidth(Region.USE_PREF_SIZE);
		swordLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		swordLabel1.setTextFill(Paint.valueOf("White"));
		
		Label swordLabel2 = new Label("Sword kills enemy when on the same grid.");
		root.add(swordLabel2, 10, 46);
		swordLabel2.setMinWidth(Region.USE_PREF_SIZE);
		swordLabel2.setMaxWidth(Region.USE_PREF_SIZE);
		swordLabel2.setTextFill(Paint.valueOf("White"));

		Label keyLabel = new Label("Key can open doors. Carry one key at a time.");
		root.add(keyLabel, 10, 54);
		keyLabel.setMinWidth(Region.USE_PREF_SIZE);
		keyLabel.setMaxWidth(Region.USE_PREF_SIZE);
		keyLabel.setTextFill(Paint.valueOf("White"));
		
		Label keyLabel1 = new Label("Key disappears after opening door.");
		root.add(keyLabel1, 10, 58);
		keyLabel1.setMinWidth(Region.USE_PREF_SIZE);
		keyLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		keyLabel1.setTextFill(Paint.valueOf("White"));

		Label treasureLabel = new Label("Pick up all treasures to fulfill the goal.");
		root.add(treasureLabel, 10, 66);
		treasureLabel.setMinWidth(Region.USE_PREF_SIZE);
		treasureLabel.setMaxWidth(Region.USE_PREF_SIZE);
		treasureLabel.setTextFill(Paint.valueOf("White"));
		
		Label wallLabel = new Label("Going through the wall is not an option.");
		root.add(wallLabel, 78, 6);
		wallLabel.setMinWidth(Region.USE_PREF_SIZE);
		wallLabel.setMaxWidth(Region.USE_PREF_SIZE);
		wallLabel.setTextFill(Paint.valueOf("White"));

		
		scene = new Scene(root, 750, 500);
	}

	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public TutorialScreenController getController() {
		return controller;
	}
}
