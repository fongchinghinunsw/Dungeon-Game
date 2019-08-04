package unsw.dungeon;

public class Fast implements MoveSpeed {
	private long speedFactor = 25;

	@Override
	public long getSpeedFactor() {
		return this.speedFactor;
	}

}
