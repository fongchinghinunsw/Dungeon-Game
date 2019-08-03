package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameOverScreenController {
	private Stage stage;

	@FXML
	private Button retryButton;
	@FXML
	private Button quitButton;
	@FXML
	private StackPane layout;
	private DungeonScreen dungeonScreen;
	private StartScreen startScreen;

	public GameOverScreenController(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void handleRetryButton() throws IOException {
		dungeonScreen.start();
	}
	
	@FXML
	public void handleQuitButton() throws IOException {
		startScreen.start();
	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
	
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}

	@FXML
	public void initialize() {
	}
}
