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
		root.add(player, 1, 6);
		ImageView bomb = new ImageView(new Image("/bomb_unlit.png"));
		root.add(bomb, 1, 18);
		ImageView potion = new ImageView(new Image("/brilliant_blue_new.png"));
		root.add(potion, 1, 30);
		ImageView sword = new ImageView(new Image("/greatsword_1_new.png"));
		root.add(sword, 1, 42);
		ImageView key = new ImageView(new Image("/key.png"));
		root.add(key, 1, 54);
		ImageView treasure = new ImageView(new Image("/gold_pile.png"));
		root.add(treasure, 1, 66);
		ImageView wall = new ImageView(new Image("/brick_brown_0.png"));
		root.add(wall, 66, 6);
		ImageView boulder = new ImageView(new Image("/boulder.png"));
		root.add(boulder, 66, 18);
		ImageView enemy = new ImageView(new Image("/hound.png"));
		root.add(enemy, 66, 30);
		ImageView door = new ImageView(new Image("/closed_door.png"));
		root.add(door, 66, 42);
		ImageView trigger = new ImageView(new Image("/pressure_plate.png"));
		root.add(trigger, 66, 54);
		ImageView exit = new ImageView(new Image("/exit.png"));
		root.add(exit, 66, 66);
		Label playerLabel = new Label("This is YOU. Press SPACE to pick up item.");
		root.add(playerLabel, 8, 6);
		playerLabel.setMinWidth(Region.USE_PREF_SIZE);
		playerLabel.setMaxWidth(Region.USE_PREF_SIZE);
		playerLabel.setTextFill(Paint.valueOf("White"));
		
		Label playerLabel2 = new Label("Use arrow keys to move yourself around :)");
		root.add(playerLabel2, 8, 10);
		playerLabel2.setMinWidth(Region.USE_PREF_SIZE);
		playerLabel2.setMaxWidth(Region.USE_PREF_SIZE);
		playerLabel2.setTextFill(Paint.valueOf("White"));
		
		Label bombLabel1 = new Label("Press T to use Bomb. Explodes in 3 seconds.");
		root.add(bombLabel1, 8, 18);
		bombLabel1.setMinWidth(Region.USE_PREF_SIZE);
		bombLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		bombLabel1.setTextFill(Paint.valueOf("White"));

		Label bombLabel3 = new Label("Destroys any player, enemy or boulder nearby.");
		root.add(bombLabel3, 8, 22);
		bombLabel3.setMinWidth(Region.USE_PREF_SIZE);
		bombLabel3.setMaxWidth(Region.USE_PREF_SIZE);
		bombLabel3.setTextFill(Paint.valueOf("White"));
		
		
		Label potionLabel1 = new Label("Press G to use Potion. Lasts for 5 seconds.");
		root.add(potionLabel1, 8, 30);
		potionLabel1.setMinWidth(Region.USE_PREF_SIZE);
		potionLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		potionLabel1.setTextFill(Paint.valueOf("White"));
		
		Label potionLabel2 = new Label("Enemy on the same grid with you gets killed");
		root.add(potionLabel2, 8, 34);
		potionLabel2.setMinWidth(Region.USE_PREF_SIZE);
		potionLabel2.setMaxWidth(Region.USE_PREF_SIZE);
		potionLabel2.setTextFill(Paint.valueOf("White"));

		Label swordLabel1 = new Label("Sword lasts 5 hits.");
		root.add(swordLabel1, 8, 42);
		swordLabel1.setMinWidth(Region.USE_PREF_SIZE);
		swordLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		swordLabel1.setTextFill(Paint.valueOf("White"));
		
		Label swordLabel2 = new Label("Sword kills enemy when on the same grid.");
		root.add(swordLabel2, 8, 46);
		swordLabel2.setMinWidth(Region.USE_PREF_SIZE);
		swordLabel2.setMaxWidth(Region.USE_PREF_SIZE);
		swordLabel2.setTextFill(Paint.valueOf("White"));

		Label keyLabel = new Label("Key can open doors. Carry one key at a time.");
		root.add(keyLabel, 8, 54);
		keyLabel.setMinWidth(Region.USE_PREF_SIZE);
		keyLabel.setMaxWidth(Region.USE_PREF_SIZE);
		keyLabel.setTextFill(Paint.valueOf("White"));
		
		Label keyLabel1 = new Label("Key disappears after opening door.");
		root.add(keyLabel1, 8, 58);
		keyLabel1.setMinWidth(Region.USE_PREF_SIZE);
		keyLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		keyLabel1.setTextFill(Paint.valueOf("White"));

		Label treasureLabel = new Label("Pick up all treasures to fulfill the goal.");
		root.add(treasureLabel, 8, 66);
		treasureLabel.setMinWidth(Region.USE_PREF_SIZE);
		treasureLabel.setMaxWidth(Region.USE_PREF_SIZE);
		treasureLabel.setTextFill(Paint.valueOf("White"));
		
		Label wallLabel = new Label("Going through the wall is not an option.");
		root.add(wallLabel, 78, 6);
		wallLabel.setMinWidth(Region.USE_PREF_SIZE);
		wallLabel.setMaxWidth(Region.USE_PREF_SIZE);
		wallLabel.setTextFill(Paint.valueOf("White"));
		
		Label boulderLabel = new Label("Boulders can be pushed.");
		root.add(boulderLabel, 78, 18);
		boulderLabel.setMinWidth(Region.USE_PREF_SIZE);
		boulderLabel.setMaxWidth(Region.USE_PREF_SIZE);
		boulderLabel.setTextFill(Paint.valueOf("White"));
		
		Label boulderLabel1 = new Label("You can only push one boulder at a time.");
		root.add(boulderLabel1, 78, 22);
		boulderLabel1.setMinWidth(Region.USE_PREF_SIZE);
		boulderLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		boulderLabel1.setTextFill(Paint.valueOf("White"));

		Label enemyLabel1 = new Label("Enemy moves towards you.");
		root.add(enemyLabel1, 78, 30);
		enemyLabel1.setMinWidth(Region.USE_PREF_SIZE);
		enemyLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		enemyLabel1.setTextFill(Paint.valueOf("White"));
		
		Label enemyLabel2 = new Label("Turning into Hulk scares it away.");
		root.add(enemyLabel2, 78, 34);
		enemyLabel2.setMinWidth(Region.USE_PREF_SIZE);
		enemyLabel2.setMaxWidth(Region.USE_PREF_SIZE);
		enemyLabel2.setTextFill(Paint.valueOf("White"));
		
		Label doorLabel1 = new Label("Closed doors stop you.");
		root.add(doorLabel1, 78, 42);
		doorLabel1.setMinWidth(Region.USE_PREF_SIZE);
		doorLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		doorLabel1.setTextFill(Paint.valueOf("White"));
		
		Label doorLabel2 = new Label("Doors can be opened by a specific key.");
		root.add(doorLabel2, 78, 46);
		doorLabel2.setMinWidth(Region.USE_PREF_SIZE);
		doorLabel2.setMaxWidth(Region.USE_PREF_SIZE);
		doorLabel2.setTextFill(Paint.valueOf("White"));
		
		Label triggerLabel1 = new Label("Trigger the trigger with a boulder.");
		root.add(triggerLabel1, 78, 54);
		triggerLabel1.setMinWidth(Region.USE_PREF_SIZE);
		triggerLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		triggerLabel1.setTextFill(Paint.valueOf("White"));
		
		Label triggerLabel2 = new Label("Trigger all triggers to fulfill the goal.");
		root.add(triggerLabel2, 78, 58);
		triggerLabel2.setMinWidth(Region.USE_PREF_SIZE);
		triggerLabel2.setMaxWidth(Region.USE_PREF_SIZE);
		triggerLabel2.setTextFill(Paint.valueOf("White"));
		
		Label exitLabel1 = new Label("Move to the exit to fulfill the goal.");
		root.add(exitLabel1, 78, 66);
		exitLabel1.setMinWidth(Region.USE_PREF_SIZE);
		exitLabel1.setMaxWidth(Region.USE_PREF_SIZE);
		exitLabel1.setTextFill(Paint.valueOf("White"));
		
		Label exitLabel2 = new Label("Finish the other tasks first though!");
		root.add(exitLabel2, 78, 70);
		exitLabel2.setMinWidth(Region.USE_PREF_SIZE);
		exitLabel2.setMaxWidth(Region.USE_PREF_SIZE);
		exitLabel2.setTextFill(Paint.valueOf("White"));
		
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
