package unsw.dungeon;

public class Mage extends Enemy {

	public Mage(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setSpeedFactor(new Slow());
	}

}
