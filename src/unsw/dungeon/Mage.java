package unsw.dungeon;

/**
 * Mage is an enemy that walks in slow
 * 
 * @author z5211173
 *
 */
public class Mage extends Enemy {

	/**
	 * constructor for mage
	 * 
	 * @param dungeon
	 * @param x
	 * @param y
	 */
	public Mage(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setSpeedFactor(new Slow());
	}

}
