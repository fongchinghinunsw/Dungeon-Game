package unsw.dungeon;

/**
 * Uneuipped state of the equipable.
 *
 * A basic prototype to serve as the representation of unequipped state of the
 * Equipable.
 *
 * @author Stephen Fong
 *
 */
public class UnequippedState implements EquipState {

	/*
	 * return true for the equipable that has not been equipped.
	 */
	@Override
	public boolean equip() {
		return true;
	}

	/*
	 * return false for the equipable that has been equipped.
	 */
	@Override
	public boolean unequip() {
		return false;
	}

}
