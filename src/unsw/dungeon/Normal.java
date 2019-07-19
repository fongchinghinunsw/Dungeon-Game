package unsw.dungeon;

public class Normal implements MoveSpeed {
	private long speed = 5;

	@Override
	public long getSpeed() {
		return this.speed;
	}

}
