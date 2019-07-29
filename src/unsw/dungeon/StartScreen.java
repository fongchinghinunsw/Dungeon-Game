package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen {

	private Stage stage;
	private String title;
	private StartScreenController controller;
	private Scene scene;
	private DungeonScreen dungeonScreen;

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

	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public StartScreenController getController() {
		return controller;
	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
}
