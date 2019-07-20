package unsw.dungeon;

import java.util.TimerTask;

import javafx.application.Platform;

public class BombTimer extends TimerTask {
	private Bomb self;
	private int tick;
	private Player player;

	public BombTimer(Bomb self, Player player) {
		this.self = self;
		this.tick = 3;
		this.player = player;
	}

	@Override
	public void run() {
		if (tick >= 0) {
			// Prevent the ConcurrentModificationException
			Platform.runLater(() -> {
				String message = "Counting down......" + tick;
				System.out.println(message);
				player.notifyObservers();
				tick--;
			});
		} else {
			// stop the timer
			System.out.println("BOOOOOOM!!!");
			cancel();
		}

	}

}