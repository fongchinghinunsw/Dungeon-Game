package unsw.dungeon;

import java.util.TimerTask;

import javafx.application.Platform;

public class EnemyTimer extends TimerTask {
	private Enemy self;
	private Player player;

	/**
	 * PotionTimer constructor
	 * 
	 * @param potion
	 * @param player
	 */
	public EnemyTimer(Enemy self, Player player) {
		this.self = self;
		this.player = player;
	}

	@Override
	public void run() {
		if (self.isAlive().getValue()) {
			this.self.findPlayer();
			// Prevent the ConcurrentModificationException
			Platform.runLater(() -> {
				self.notifyObservers();
				player.notifyObservers();
			});
		} else {
			// stop the timer
			cancel();
		}

	}
}