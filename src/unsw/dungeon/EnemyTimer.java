package unsw.dungeon;

import java.util.TimerTask;

import javafx.application.Platform;

public class EnemyTimer extends TimerTask {
	private Dungeon dungeon;
	private Enemy self;
	private int tick;

	/**
	 * PotionTimer constructor
	 * 
	 * @param potion
	 * @param player
	 */
	public EnemyTimer(Dungeon dungeon, Enemy self) {
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