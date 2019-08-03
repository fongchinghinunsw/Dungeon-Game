package unsw.dungeon;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
		FadeTransition ft = new FadeTransition(Duration.millis(10000), layout);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
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
