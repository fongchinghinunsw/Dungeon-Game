package unsw.dungeon;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Character {

	private Dungeon dungeon;
	private Backpack backpack;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x x-coordinate of the player
	 * @param y y-coordinatee of the player
	 */
	public Player(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.dungeon = dungeon;
		this.backpack = new Backpack();

	}

	// Here for now, open to modification/deletion
	public Backpack getBackpack() {
		return this.backpack;
	}

	public void equipItem(Equipable item) {
		this.backpack.addItem(item);
	}

}
