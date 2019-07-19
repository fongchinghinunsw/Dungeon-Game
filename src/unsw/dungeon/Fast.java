package unsw.dungeon;

public class Fast implements MoveSpeed {
	private long speed = 10;

	@Override
	public long getSpeed() {
		return this.speed;
	}

}
