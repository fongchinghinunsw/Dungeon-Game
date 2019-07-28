package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenController {

	@FXML
	private Button startButton;

	@FXML
	private Button quitButton;

	@FXML
	private Button tutButton;

	private DungeonScreen dungeonScreen;

	@FXML
	public void handleStartButton(ActionEvent event) throws IOException {
		dungeonScreen.start();
	}

	@FXML
	public void handleQuitButton(ActionEvent event) {

	}

	@FXML
	public void handleTutButton(ActionEvent event) {

	}

	@FXML
	public void initialize() {

	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}

}
