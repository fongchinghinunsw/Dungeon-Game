package unsw.dungeon;

public class EquippedState implements EquipState {

	@Override
	public boolean equip() {
		return true;
	}

	@Override
	public boolean unequip() {
		return false;
	}

}
