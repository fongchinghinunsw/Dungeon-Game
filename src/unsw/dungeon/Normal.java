package unsw.dungeon;

public class Normal implements MoveSpeed {
	private long speedFactor = 50;

	@Override
	public long getSpeedFactor() {
		return this.speedFactor;
	}

}
