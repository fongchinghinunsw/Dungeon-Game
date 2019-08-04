package unsw.dungeon;

/**
 * key has an id, and can open the door with the same id
 * 
 * @author z5211173
 *
 */
public class Key extends Equipable implements Observer {

	private final int id;

	/**
	 * constructor for key
	 * 
	 * @param dungeon
	 * @param x
	 * @param y
	 */
	public Key(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.id = dungeon.numKey();
	}

	/**
	 * getter for id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	@Override
	public String getClassName() {
		return "Key";
	}

	@Override
	public void update(Subject obj) {
		System.out.println("Holly crap, the player is now standing on me...");
	}

}
