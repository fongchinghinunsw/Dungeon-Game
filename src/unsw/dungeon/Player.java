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
	private MoveSpeed speed;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x x-coordinate of the player
	 * @param y y-coordinate of the player
	 */
	public Player(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.dungeon = dungeon;
		this.backpack = new Backpack();
		this.speed = new Fast();

	}

	// Here for now, open to modification/deletion
	public Backpack getBackpack() {
		return this.backpack;
	}

	public void equipItem(Equipable item) {
		this.backpack.addItem(item);
	}

	@Override
	public String getClassName() {
		return "Player";
	}

	public int getSpeed() {
		return this.speed.getSpeed();
	}
}
