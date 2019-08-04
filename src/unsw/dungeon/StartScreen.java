package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A representation of the starting menu.
 *
 * A basic prototype to serve as the representation of a start screen.
 *
 * @author Stephen Fong
 *
 */
public class StartScreen {

	private Stage stage;
	private String title;
	private StartScreenController controller;
	private Scene scene;
	private DungeonScreen dungeonScreen;

	/*
	 * Constructor of the start screen.
	 */
	public StartScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Dungeon";

		controller = new StartScreenController(stage);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScreen.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		Parent root = loader.load();
		scene = new Scene(root, 750, 400);
	}

	/*
	 * Loading the starting menu.
	 */
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	/*
	 * Get the controller of the starting menu.
	 */
	public StartScreenController getController() {
		return controller;
	}

	/*
	 * Set the dungeon screen of the starting menu.
	 */
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
}
