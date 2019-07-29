package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DungeonScreen {

	private Stage stage;
	private String title;
	private DungeonScreenController controller;
	private Scene scene;
	private StartScreen startScreen;

	public DungeonScreen(Stage stage) throws IOException {
		this.stage = stage;
		stage.setTitle("Dungeon");

	}

	public void start() throws IOException {
		// load the game only after the player press the start button.
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");

		DungeonController controller = dungeonLoader.loadController();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(controller);

		GridPane root = loader.load();
		HBox topMenu = new HBox(5);

		String style = "-fx-background-color: linear-gradient(#2980B9,#6DD5FA,#FFFFFF);";
		topMenu.setStyle(style);
		topMenu.setAlignment(Pos.TOP_RIGHT);
		Button restartButton = new Button("Restart");
		Button quitButton = new Button("Quit");
		Button pauseButton = new Button("Pause");

		restartButton.setOnMouseClicked(e -> {
			try {
				handleRestartButton();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		quitButton.setOnMouseClicked(e -> handleQuitButton());

		// quitButton.setPadding(new Insets(0, 10, 0, 0));
		topMenu.getChildren().addAll(restartButton, quitButton, pauseButton);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);
		borderPane.setCenter(root);

		scene = new Scene(borderPane);
		root.requestFocus();
		stage.setTitle("Dungeon");
		stage.setScene(scene);
		stage.show();
	}

	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}

	public void handleRestartButton() throws IOException {
		this.start();
	}

	public void handleQuitButton() {
		startScreen.start();
	}

	public DungeonScreenController getController() {
		return controller;
	}
}
