package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WinScreen {

	private Stage stage;
	private String title;
	private WinScreenController controller;
	private Scene scene;
	private StackPane layout;

	public WinScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "You Win!";

		controller = new WinScreenController(stage);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Win.fxml"));
		loader.setController(controller);
		layout = loader.load();
		scene = new Scene(layout, 750, 400);
		Label congratLabel = new Label("Congratulations!");
		congratLabel.setWrapText(true);
		congratLabel.setStyle("-fx-font-family: \"Ariel\"; -fx-font-size: 30; -fx-text-fill: white;");
		VBox congratBox = new VBox(congratLabel);
		congratBox.setMaxWidth(300);
		congratBox.setAlignment(Pos.TOP_CENTER);
		congratBox.setPadding(new Insets(20, 0, 30, 0));
		layout.getChildren().add(congratBox);
		Label messageLabel = new Label("You put a sword through the Trimestaurus' heart, and as you \n"
				+ "walked away you heard it utter with its last breath, 'But UNSW \n"
				+ "has conducted comprehensive student surveys to measure \n"
				+ "preferences on different calendar models and to obtain \n"
				+ "feedback on the UNSW3+ calendar.[sic]'\n"
				+ "You've slain the beast and brought peace to this land,\n" + "everyone lived happily ever after.\n\n"
				+ "THE END.");
		messageLabel.setWrapText(true);
		messageLabel
				.setStyle("-fx-font-family: \"Ariel\"; -fx-font-size: 15; -fx-text-fill: white;-fx-line-spacing: 1em;");
		VBox messageBox = new VBox(messageLabel);
		messageBox.setMaxWidth(500);
		messageBox.setAlignment(Pos.CENTER);
		messageBox.setPadding(new Insets(50, 0, 30, 0));
		layout.getChildren().add(messageBox);
	}

	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public WinScreenController getController() {
		return controller;
	}
}
