package unsw.dungeon;

public class Hound extends Enemy {

	public Hound(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setSpeedFactor(new Normal());
	}

}
