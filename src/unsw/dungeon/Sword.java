package unsw.dungeon;

/**
 * Sword entity of the dungeon.
 *
 * A basic prototype to serve as the representation of a sword.
 *
 * @author Stephen Fong
 *
 */
public class Sword extends Equipable implements Observer {

	private int durability;

	/*
	 * Constructor of a sword.
	 */
	public Sword(int x, int y) {
		super(x, y);
		this.durability = 5;
	}

	/*
	 * Get the remaining durability of the sword.
	 * 
	 * @return remaining amount of durability
	 */
	public int getDurability() {
		return this.durability;
	}

	/*
	 * Reduce the durability of the sword.
	 */
	public void reduceDurability() {
		if (durability > 0) {
			this.durability--;
		}
	}

	/*
	 * Check whether can equip the sword.
	 * 
	 * @return whether the sword can be equipped or not
	 */
	@Override
	public boolean equip() {
		if (state.equip()) {
			setState(equippedState);
			return true;
		}
		return false;
	}

	/*
	 * Receive update from the subject.
	 */
	@Override
	public void update(Subject obj) {
		System.out.println("You see a sword.");
	}

	/*
	 * Get the class name of a sword.
	 */
	@Override
	public String getClassName() {
		return "Sword";
	}

}
