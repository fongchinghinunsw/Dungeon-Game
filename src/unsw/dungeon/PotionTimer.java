package unsw.dungeon;

import java.util.TimerTask;

public class PotionTimer extends TimerTask {
	private int MAX_SECONDS;
	private Player player;
	int seconds = 0;

	/**
	 * PotionTimer constructor
	 * 
	 * @param potion
	 * @param player
	 */
	public PotionTimer(Potion potion, Player player) {
		this.MAX_SECONDS = potion.getTime();
		this.player = player;
	}

	@Override
	public void run() {
		if (seconds < MAX_SECONDS) {
			seconds++;
		} else {
			// stop the timer
			System.out.println("You're not invincible anymore");
			this.player.disablePotion();
			cancel();
		}
	}
}
