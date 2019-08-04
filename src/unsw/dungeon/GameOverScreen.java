package unsw.dungeon;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Game over screen contains retry button, quit button Fades in when the player
 * dies, with a delay of 0.8 seconds
 * 
 * @author z5211173
 *
 */
public class GameOverScreen {
	private Stage stage;
	private String title;
	private GameOverScreenController controller;
	private Scene scene;
	private StackPane layout;

	/**
	 * Constructor for game over screen
	 * 
	 * @param stage
	 * @throws IOException
	 */
	public GameOverScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Game Over";

		controller = new GameOverScreenController(stage);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
		loader.setController(controller);
		layout = loader.load();
		scene = new Scene(layout, 750, 400);
	}

	/**
	 * getter for controller
	 * 
	 * @return controller
	 */
	public GameOverScreenController getController() {
		return controller;
	}

	/**
	 * starts the screen
	 */
	public void start() {
		FadeTransition ft = new FadeTransition(Duration.millis(8000), layout);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		stage.setTitle(title);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
