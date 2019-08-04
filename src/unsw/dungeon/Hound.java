package unsw.dungeon;

/**
 * hound runs Normal
 * 
 * @author z5211173
 *
 */
public class Hound extends Enemy {

	/**
	 * constructor for hound
	 * 
	 * @param dungeon
	 * @param x
	 * @param y
	 */
	public Hound(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.setSpeedFactor(new Normal());
	}

}
