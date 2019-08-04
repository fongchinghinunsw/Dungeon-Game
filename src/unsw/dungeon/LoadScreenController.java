package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Controller for load screen, handles skip button
 * 
 * @author z5211173
 *
 */
public class LoadScreenController {
	private Stage stage;

	@FXML
	private Button skipButton;
	@FXML
	private StackPane layout;
	private DungeonScreen dungeonScreen;
	private LoadScreen loadScreen;

	/**
	 * constructor for loadScreenController
	 * 
	 * @param stage
	 */
	public LoadScreenController(Stage stage) {
		this.stage = stage;
	}

	/**
	 * goes to dungeonScreen when skip pressed
	 * 
	 * @throws IOException
	 */
	@FXML
	public void handleSkipButton() throws IOException {
		dungeonScreen.start();
		loadScreen.stopTimer();
	}

	/**
	 * setter for dungeonScreen
	 * 
	 * @param dungeonScreen
	 */
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}

	/**
	 * setter for loadScreen
	 * 
	 * @param loadScreen
	 */
	public void setLoadScreen(LoadScreen loadScreen) {
		this.loadScreen = loadScreen;
	}

	@FXML
	public void initialize() {
	}
}
