package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the starting menu.
 *
 * A basic prototype to serve as the representation of controller of the
 * starting menu.
 *
 * @author Stephen Fong
 *
 */
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

	/*
	 * Constructor of the start screen controller.
	 */
	public StartScreenController(Stage stage) {
		this.stage = stage;
	}

	/*
	 * Handle the start button.
	 */
	@FXML
	public void handleStartButton(ActionEvent event) throws IOException {
		loadScreen.start();
	}

	/*
	 * Handle the quit button.
	 */
	@FXML
	public void handleQuitButton(ActionEvent event) {
		stage.close();
	}

	/*
	 * Handle the tutorial button.
	 */
	@FXML
	public void handleTutButton(ActionEvent event) throws IOException {
		tutorialScreen.start();
	}

	/*
	 * Set the loading screen for the start screen.
	 */
	public void setLoadScreen(LoadScreen loadScreen) {
		this.loadScreen = loadScreen;
	}

	/*
	 * Set the tutorial screen for the starting menu.
	 */
	public void setTutorialScreen(TutorialScreen tutorialScreen) {
		this.tutorialScreen = tutorialScreen;
	}
}
