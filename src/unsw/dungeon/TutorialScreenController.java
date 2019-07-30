package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TutorialScreenController {
	private Stage stage;

	@FXML
	private Button quitButton;

	private StartScreen startScreen;

	public TutorialScreenController(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void handleQuitButton() {
		startScreen.start();
	}

	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}

	@FXML
	public void initialize() {

	}
}
