package unsw.dungeon;

/**
 * An equipable item is an item that can be picked up by the player. This
 * includes sword,treasure,key,bomb and potion
 * 
 * @author z5211173
 *
 */
public class Equipable extends Entity {

	EquipState state;

	EquipState equippedState;

	EquipState unEquippedState;

	/**
	 * Constructor for equipable
	 * 
	 * @param x
	 * @param y
	 */
	public Equipable(int x, int y) {
		super(x, y);
		equippedState = new EquippedState();
		unEquippedState = new UnequippedState();
		state = unEquippedState;
	}

	/**
	 * getter for state
	 * 
	 * @return states
	 */
	public EquipState getState() {
		return state;
	}

	/**
	 * setter for state
	 * 
	 * @param state
	 */
	public void setState(EquipState state) {
		this.state = state;
	}

	/**
	 * getter for equipped state
	 * 
	 * @return equipped state
	 */
	public EquipState getEquippedState() {
		return equippedState;
	}

	/**
	 * getter for unequipped state
	 * 
	 * @return unequipped state
	 */
	public EquipState getUnequippedState() {
		return unEquippedState;
	}

	/**
	 * equips the item
	 * 
	 * @return true if equipped
	 */
	public boolean equip() {
		if (state.equip()) {
			setState(equippedState);
			return true;
		}
		return false;
	}

	/**
	 * unequips an item
	 * 
	 * @return true if unequipped
	 */
	public boolean unequip() {
		if (state.unequip()) {
			setState(unEquippedState);
			return true;
		}
		return false;
	}

	/**
	 * returns the name of this class
	 */
	public String getClassName() {
		return "Equipable";
	}

}
