package unsw.dungeon;

import java.util.TimerTask;

import javafx.application.Platform;

public class EnemyTimer extends TimerTask {
	private Enemy self;
	private int tick;
	private Player player;

	/**
	 * PotionTimer constructor
	 * 
	 * @param potion
	 * @param player
	 */
	public EnemyTimer(Enemy self, Player player) {
		this.self = self;
		this.tick = 0;
		this.player = player;
	}

	@Override
	public void run() {
		if (self.isAlive().getValue()) {
			// Prevent the ConcurrentModificationException
			Platform.runLater(() -> {
				if (this.tick % 10 == 0) {
					this.self.findPlayer();
				}
				self.notifyObservers();
				player.notifyObservers();
				tick++;
			});
		} else {
			// stop the timer
			cancel();
		}

	}

}