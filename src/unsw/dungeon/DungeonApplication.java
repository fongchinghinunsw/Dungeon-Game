package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		StartScreen startScreen = new StartScreen(primaryStage);
		DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
		WinScreen winScreen = new WinScreen(primaryStage);
		dungeonScreen.setStartScreen(startScreen);
		TutorialScreen tutorialScreen = new TutorialScreen(primaryStage);
		tutorialScreen.getController().setStartScreen(startScreen);
		startScreen.getController().setTutorialScreen(tutorialScreen);
		LoadScreen loadScreen = new LoadScreen(primaryStage);
		GameOverScreen gameOverScreen = new GameOverScreen(primaryStage);
		gameOverScreen.getController().setDungeonScreen(dungeonScreen);
		gameOverScreen.getController().setStartScreen(startScreen);
		loadScreen.getController().setDungeonScreen(dungeonScreen);
		loadScreen.getController().setLoadScreen(loadScreen);
		startScreen.getController().setLoadScreen(loadScreen);
		dungeonScreen.setGameOverScreen(gameOverScreen);
		dungeonScreen.setWinScreen(winScreen);
		winScreen.getController().setDungeonScreen(dungeonScreen);
		winScreen.getController().setStartScreen(startScreen);
		startScreen.start();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
