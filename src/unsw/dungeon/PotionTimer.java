package unsw.dungeon;

import java.util.TimerTask;

public class PotionTimer extends TimerTask {
	private int MAX_SECONDS;
	private Player player;

	public PotionTimer(Potion potion, Player player) {
		this.MAX_SECONDS = potion.getTime();
		this.player = player;
	}

	int seconds = 0;

	@Override
	public void run() {
		if (seconds < MAX_SECONDS) {
			System.out.println("Seconds = " + seconds);
			seconds++;
		} else {
			// stop the timer
			System.out.println("Effect of potion should wear off by now");
			this.player.disablePotion();
			cancel();
		}
	}
}
