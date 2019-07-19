package unsw.dungeon;

public class Slow implements MoveSpeed {
	// set to 1 for now
	private int speed = 1;

	@Override
	public int getSpeed() {
		return this.speed;
	}

}
