package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * controller for game over screen. handles quit and restart button
 * 
 * @author z5211173
 *
 */
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

	/**
	 * constructor for cntroller
	 * 
	 * @param stage
	 */
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

	/**
	 * setter for dungeon screen
	 * 
	 * @param dungeonScreen
	 */
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}

	/**
	 * setter for start screen
	 * 
	 * @param startScreen
	 */
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}

	@FXML
	public void initialize() {
	}
}
