package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {

	private Stage stage;
	private String title;
	private DungeonScreenController controller;
	private Scene scene;

	public DungeonScreen(Stage stage) throws IOException {
		this.stage = stage;
		stage.setTitle("Dungeon");

	}

	public void start() throws IOException {
		// load the game only after the player press the start button.
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");

		DungeonController controller = dungeonLoader.loadController();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		scene = new Scene(root);
		root.requestFocus();
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}

	public DungeonScreenController getController() {
		return controller;
	}
}
