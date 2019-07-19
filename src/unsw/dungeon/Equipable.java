package unsw.dungeon;

public class Equipable extends Entity {

	EquipState equippedState;

	EquipState unEquippedState;

	EquipState state;

	public Equipable(int x, int y) {
		super(x, y);
		equippedState = new EquippedState();
		unEquippedState = new UnequippedState();
		state = unEquippedState;
	}

	public EquipState getState() {
		return state;

	}

	public void setState(EquipState state) {
		this.state = state;
	}

	public EquipState getEquippedState() {
		return equippedState;
	}

	public EquipState getUnequippedState() {
		return unEquippedState;
	}

	public boolean equip() {
		if (state.equip()) {
			setState(equippedState);
			return true;
		}
		return false;
	}

	public String getClassName() {
		return "Equipable";
	}

}
