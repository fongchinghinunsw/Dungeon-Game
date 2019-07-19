package unsw.dungeon;

public class Slow implements MoveSpeed {
	// set to 1 for now
	private long speed = 1;

	@Override
	public long getSpeed() {
		return this.speed;
	}

}
