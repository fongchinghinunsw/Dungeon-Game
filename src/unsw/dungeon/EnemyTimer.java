package unsw.dungeon;

import java.util.TimerTask;

import javafx.application.Platform;

public class EnemyTimer extends TimerTask {
	private Enemy self;
	private int tick;
	private Dungeon dungeon;

	/**
	 * PotionTimer constructor
	 * 
	 * @param potion
	 * @param player
	 */
	public EnemyTimer(Enemy self, Dungeon dungeon) {
		this.self = self;
		this.tick = 0;
		this.dungeon = dungeon;
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
				tick++;
			});
		} else {
			// stop the timer
			cancel();
		}

	}

}