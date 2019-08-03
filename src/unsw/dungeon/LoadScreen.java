package unsw.dungeon;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadScreen {

	@FXML
	private StackPane layout;
	private Stage stage;
	private String title;
	private LoadScreenController controller;
	private Scene scene;
	private Timeline loadScreenTimer;
	private int timerTick;
	private StringProperty loadMessage;

	public LoadScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Loading Screen";
		loadMessage = new SimpleStringProperty("Loading.");
		controller = new LoadScreenController(stage);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Loading.fxml"));
		loader.setController(controller);
		timerTick = 0;

		// load into a Parent node called root
		layout = loader.load();
		scene = new Scene(layout, 900, 500);

		Label label = new Label();
		label.textProperty().bind(loadMessage);
		label.setWrapText(true);
		label.setStyle("-fx-font-family: \"Ariel\"; -fx-font-size: 23; -fx-text-fill: white;");
		VBox loadBox = new VBox(label);
		loadBox.setAlignment(Pos.TOP_CENTER);
		label.setLayoutY(100.0);
//		layout.getChildren().add(label);
		layout.getChildren().add(loadBox);

	}

	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		timerTick = 0;
		if (loadScreenTimer != null) {
			loadScreenTimer.stop();
		}
		this.loadScreenTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			if (timerTick == 6) {
				controller.handleSkipButton();
				System.out.println("An attempt has been made");
				loadScreenTimer.stop();
			}
			switch (timerTick % 3) {
			case 2:
				loadMessage.set("Loading.");
				break;
			case 0:
				loadMessage.set("Loading..");
				break;
			case 1:
				loadMessage.set("Loading...");
				break;
			default:
				break;
			}
			System.out.printf("Counting down......%d\n", 5 - timerTick);
			timerTick++;
		}));
		loadScreenTimer.setCycleCount(Timeline.INDEFINITE);
		loadScreenTimer.play();
	}

	public LoadScreenController getController() {
		return controller;
	}
}
