package unsw.dungeon;

public class Normal implements MoveSpeed {
	private long speed = 50;

	@Override
	public long getSpeed() {
		return this.speed;
	}

}
