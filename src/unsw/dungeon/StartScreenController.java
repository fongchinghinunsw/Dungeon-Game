package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartScreenController {

	private Stage stage;

	@FXML
	private Button startButton;

	@FXML
	private Button quitButton;

	@FXML
	private Button tutButton;

	private LoadScreen loadScreen;

	private TutorialScreen tutorialScreen;

	public StartScreenController(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void handleStartButton(ActionEvent event) throws IOException {
		loadScreen.start();
	}

	@FXML
	public void handleQuitButton(ActionEvent event) {
		stage.close();
	}

	@FXML
	public void handleTutButton(ActionEvent event) throws IOException {
		tutorialScreen.start();
	}

	@FXML
	public void initialize() {

	}

	public void setLoadScreen(LoadScreen loadScreen) {
		this.loadScreen = loadScreen;
	}

	public void setTutorialScreen(TutorialScreen tutorialScreen) {
		this.tutorialScreen = tutorialScreen;
	}
}
