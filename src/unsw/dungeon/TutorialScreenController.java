package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Controller for the tutorial, handle events in the tutorial screen.
 *
 * @author Stephen Fong
 *
 */
public class TutorialScreenController {
	private Stage stage;

	@FXML
	private GridPane squares;
	@FXML
	private Button quitButton;

	private StartScreen startScreen;

	/*
	 * Constructor for the tutorial screen controller.
	 */
	public TutorialScreenController(Stage stage) {
		this.stage = stage;
	}

	/*
	 * handle the quit button.
	 */
	@FXML
	public void handleQuitButton() {
		startScreen.start();
	}

	/*
	 * set up the start screen for the controller.
	 */
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}
}
