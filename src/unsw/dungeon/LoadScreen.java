package unsw.dungeon;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadScreen {
	private Stage stage;
	private String title;
	private LoadScreenController controller;
	private Scene scene;
	private Timeline loadScreenTimer;
	private int timerTick;

	public LoadScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Loading Screen";

		controller = new LoadScreenController(stage);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Loading.fxml"));
		loader.setController(controller);
		timerTick = 0;

		// load into a Parent node called root
		Parent root = loader.load();
		scene = new Scene(root, 900, 500);
	}

	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.setResizable(false);
		this.loadScreenTimer = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
			// the higher the speed is the more frequent the enemy moves
			if (timerTick == 5) {
				controller.handleSkipButton();
			}
			timerTick++;
		}));
		loadScreenTimer.play();
		stage.show();
	}
	public void startTimer() {
		this.loadScreenTimer = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
			// the higher the speed is the more frequent the enemy moves
			if (timerTick == 5) {
				controller.handleSkipButton();
			}
			timerTick++;
		}));
		loadScreenTimer.play();
	}
	public LoadScreenController getController() {
		return controller;
	}
}
