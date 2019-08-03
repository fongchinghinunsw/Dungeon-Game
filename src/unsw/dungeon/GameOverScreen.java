package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameOverScreen {
	private Stage stage;
	private String title;
	private GameOverScreenController controller;
	private Scene scene;
	private StackPane layout;

	public GameOverScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Game Over";

		controller = new GameOverScreenController(stage);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
		loader.setController(controller);
		layout = loader.load();
		scene = new Scene(layout, 750, 400);
	}
	
	public GameOverScreenController getController() {
		return controller;
	}

	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
