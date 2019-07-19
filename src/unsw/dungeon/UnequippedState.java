package unsw.dungeon;

public class UnequippedState implements EquipState {

	@Override
	public boolean equip() {
		return true;
	}

	@Override
	public boolean unequip() {
		return false;
	}

}
