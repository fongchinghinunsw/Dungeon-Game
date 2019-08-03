package unsw.dungeon;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
		scene = new Scene(layout, 750, 400);

		Label label = new Label();
		label.textProperty().bind(loadMessage);
		label.setWrapText(true);
		label.setStyle("-fx-font-family: \"Ariel\"; -fx-font-size: 18; -fx-text-fill: white;");
		VBox loadBox = new VBox(label);
		loadBox.setMaxWidth(90);
		loadBox.setAlignment(Pos.BOTTOM_CENTER);
		loadBox.setPadding(new Insets(20, 0, 30, 0));
		layout.getChildren().add(loadBox);
		String doYouKnow = "Tips:\n" + "Having three terms per academic year \ncan help students learn.";
		Label dykLabel = new Label(doYouKnow);
		dykLabel.setStyle("-fx-font-family: \"Helvetica\"; -fx-font-size: 12; -fx-text-fill: white;");
		VBox tipsBox = new VBox(dykLabel);
		tipsBox.setMaxWidth(300);
		tipsBox.setMaxHeight(120);
		tipsBox.setPadding(new Insets(0, 0, -75, 0));
		tipsBox.setAlignment(Pos.BOTTOM_CENTER);
		layout.getChildren().add(tipsBox);
		String intro = "You fell asleep while writing articles criticising the \n"
				+ "trimester system. When you wake up, you see yourself in a\n"
				+ "dungeon. Inside the dungeon lies the most fearsome monster\n"
				+ "Trimestaurus. This monster has shape resembling the \n"
				+ "nonexistent creature called giraffe(r/Giraffesdontexist) \n"
				+ "and it roars like a kazoo. You must fight your way through \n"
				+ "the dungeon and defeat Trimestaurus, or you'll keep sleeping \n" + "and your coffee will get cold.";
		Label introLabel = new Label(intro);
		introLabel.setStyle(
				"-fx-font-family: \"Georgia, Serif\"; -fx-font-size: 14; -fx-text-fill: white;-fx-line-spacing: 1em;");
		VBox introBox = new VBox(introLabel);
		introBox.setAlignment(Pos.TOP_LEFT);
		introBox.setMaxWidth(600);
		introBox.setPadding(new Insets(30, 30, 0, 0));
		layout.getChildren().add(introBox);
		introBox.toBack();
		tipsBox.toBack();
		loadBox.toBack();

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
			if (timerTick == 5) {
				try {
					controller.handleSkipButton();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
			timerTick++;
		}));
		loadScreenTimer.setCycleCount(Timeline.INDEFINITE);
		loadScreenTimer.play();
	}

	public LoadScreenController getController() {
		return controller;
	}
}
