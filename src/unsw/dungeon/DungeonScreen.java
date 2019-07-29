package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
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
		HBox topMenu = new HBox();
		Button buttonA = new Button("Quit");
		Button buttonB = new Button("Edit");
		Button buttonC = new Button("View");
		topMenu.getChildren().addAll(buttonA, buttonB, buttonC);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);
		borderPane.setCenter(root);

		scene = new Scene(borderPane);
		root.requestFocus();
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}

	public DungeonScreenController getController() {
		return controller;
	}
}
