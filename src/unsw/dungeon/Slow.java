package unsw.dungeon;

public class Slow implements MoveSpeed {
	// set to 1 for now
	private long speedFactor = 100;

	@Override
	public long getSpeedFactor() {
		return this.speedFactor;
	}

}
