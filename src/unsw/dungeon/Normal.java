package unsw.dungeon;

public class Normal implements MoveSpeed {
	private int speed = 2;

	@Override
	public int getSpeed() {
		return this.speed;
	}

}
