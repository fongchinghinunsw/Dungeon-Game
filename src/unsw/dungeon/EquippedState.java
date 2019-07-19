package unsw.dungeon;

public class EquippedState implements EquipState {

	@Override
	public boolean equip() {
		return false;
	}

	@Override
	public boolean unequip() {
		return true;
	}

}
