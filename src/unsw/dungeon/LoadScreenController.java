package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoadScreenController {
	private Stage stage;
	@FXML
	private GridPane squares;
	@FXML
	private Button skipButton;

	private StartScreen startScreen;

	public LoadScreenController(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void handleSkipButton() {
		startScreen.start();
	}
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}

	@FXML
	public void initialize() {

	}
}
