package unsw.dungeon;

public class Fast implements MoveSpeed {
	private long speedFactor = 10;

	@Override
	public long getSpeedFactor() {
		return this.speedFactor;
	}

}
