package unsw.dungeon;

import java.io.IOException;

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
	private DungeonScreen dungeonScreen;
	private LoadScreen loadScreen;

	public LoadScreenController(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void handleSkipButton() throws IOException {
		dungeonScreen.start();
		loadScreen.stopTimer();
	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}

	public void setLoadScreen(LoadScreen loadScreen) {
		this.loadScreen = loadScreen;
	}
	
	@FXML
	public void initialize() {
	}
}
