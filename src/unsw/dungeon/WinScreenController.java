package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Controller for the winning screen.
 *
 * Control all the controllers in the game winning screen.
 *
 * @author Stephen Fong
 *
 */
public class WinScreenController {

	private Stage stage;
	@FXML
	private Button retryButton;
	@FXML
	private Button quitButton;
	@FXML
	private StackPane layout;
	private DungeonScreen dungeonScreen;
	private StartScreen startScreen;

	/*
	 * Constructor of the win screen controller
	 */
	public WinScreenController(Stage stage) {
		this.stage = stage;
	}

	/*
	 * Handle the retry button.
	 */
	@FXML
	public void handleRetryButton() throws IOException {
		dungeonScreen.start();
	}

	/*
	 * Handle the quit button.
	 */
	@FXML
	public void handleQuitButton() throws IOException {
		startScreen.start();
	}

	/*
	 * Set up the dungeon screen for the controller.
	 */
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}

	/*
	 * Set up the start screen for the controller.
	 */
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}
}
