package unsw.dungeon;

import java.util.TimerTask;

import javafx.application.Platform;

public class EnemyTimer extends TimerTask {
	private Enemy self;
	private int tick;

	/**
	 * PotionTimer constructor
	 * 
	 * @param potion
	 * @param player
	 */
	public EnemyTimer(Enemy self, Player player) {
		this.self = self;
		this.tick = 0;
	}

	@Override
	public void run() {
		if (self.isAlive().getValue()) {
			if (this.tick % 10 == 0)
				this.self.findPlayer();
			this.self.checkPlayer();
			tick++;
			// Prevent the ConcurrentModificationException
			Platform.runLater(() -> self.notifyObservers());
		} else {
			// stop the timer
			System.out.println("I die.");
			cancel();
		}

	}
}