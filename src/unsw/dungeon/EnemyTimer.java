package unsw.dungeon;

import java.util.TimerTask;

public class EnemyTimer extends TimerTask {
	private Enemy self;

	/**
	 * PotionTimer constructor
	 * 
	 * @param potion
	 * @param player
	 */
	public EnemyTimer(Enemy self, Player player) {
		this.self = self;
	}

	@Override
	public void run() {
		if (self.isAlive()) {
			System.out.println("Trying to move towards the player.");
			this.self.findPlayer();
		} else {
			// stop the timer
			System.out.println("I die.");
			cancel();
		}
	}
}