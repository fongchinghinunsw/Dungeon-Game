package unsw.dungeon;

import java.io.IOException;
import java.util.Map;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DungeonScreen {

	GridPane root;
	private Stage stage;
	private DungeonController controller;
	private Scene scene;
	private StartScreen startScreen;
	private BooleanProperty ctrlPressed = new SimpleBooleanProperty();
	private BooleanProperty QPressed = new SimpleBooleanProperty();
	private BooleanBinding bothPressed = ctrlPressed.and(QPressed);
	private int countPauseButtonPressed = 0;

	public DungeonScreen(Stage stage) throws IOException {
		this.stage = stage;
		stage.setTitle("Dungeon");

	}

	public void start() throws IOException {
		// load the game only after the player press the start button.
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");

		controller = dungeonLoader.loadController();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(controller);

		root = loader.load();
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
		pauseButton.setOnMouseClicked(e -> handlePauseButton(pauseButton, controller));

		topMenu.getChildren().addAll(restartButton, quitButton, pauseButton);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);
		borderPane.setCenter(root);

		scene = new Scene(borderPane);

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.CONTROL) {
				ctrlPressed.set(true);
			}
			if (e.getCode() == KeyCode.Q) {
				QPressed.set(true);
			}
		});

		scene.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.CONTROL) {
				ctrlPressed.set(false);
			}
			if (e.getCode() == KeyCode.Q) {
				QPressed.set(false);
			}
		});

		GridPane backpack = new GridPane();

		bothPressed.addListener((observable, oldValue, newValue) -> {

			if (newValue) {
				borderPane.setCenter(backpack);
				Map<String, Integer> map = controller.handleSeeBackpackRequest();
				int count = 0;
				for (Map.Entry<String, Integer> entry : map.entrySet()) {
					String key = entry.getKey();
					Integer value = entry.getValue();
					backpack.add(new ImageView(controller.getImages().get(key)), 0, count);
					backpack.add(new Label(value.toString()), 1, count);
					count++;
				}
			} else {
				backpack.getChildren().clear();
				borderPane.setCenter(root);
				root.requestFocus();
			}
		});

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

	void handlePauseButton(Button pauseButton, DungeonController controller) {
		countPauseButtonPressed++;
		if (countPauseButtonPressed % 2 == 0) {
			root.requestFocus();
			controller.resumeGame();
			pauseButton.setText("Pause");
		} else {
			controller.pauseGame();
			pauseButton.setText("Resume");
		}
	}

	public void handleQuitButton() {
		controller.deleteGame();
		startScreen.start();
	}

	public DungeonController getController() {
		return controller;
	}
}
