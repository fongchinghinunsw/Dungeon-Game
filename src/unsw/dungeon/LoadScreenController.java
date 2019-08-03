package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoadScreenController {
	private Stage stage;

	@FXML
	private Button skipButton;
	@FXML
	private StackPane layout;
	@FXML
	private Button refreshButton;

	private StartScreen startScreen;

	private LoadScreen loadScreen;

	public LoadScreenController(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void handleSkipButton() {
		startScreen.start();
	}

	@FXML
	public void handleRefreshButton() {
		loadScreen.start();
	}

	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}

	public void setLoadScreen(LoadScreen loadScreen) {
		this.loadScreen = loadScreen;
	}

	@FXML
	public void initialize() {
	}
}
