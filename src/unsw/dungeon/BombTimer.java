package unsw.dungeon;

import java.util.TimerTask;

import javafx.application.Platform;

public class BombTimer extends TimerTask {
	private Bomb self;
	private Player player;

	public BombTimer(Bomb self, Player player) {
		this.self = self;
		this.player = player;
	}

	@Override
	public void run() {
		if (self.getTime().get() >= 0) {
			// Prevent the ConcurrentModificationException
			Platform.runLater(() -> {
				String message = "Counting down......" + self.getTime();
				System.out.println(message);
				player.notifyObservers();
				self.decrementCountdownTime();
				;
			});
		} else {
			// stop the timer
			System.out.println("BOOOOOOM!!!");
			Platform.runLater(() -> {
				self.explode();
				self.notifyObservers();
			});
			cancel();
		}

	}

}