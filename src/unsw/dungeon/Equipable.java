package unsw.dungeon;

public abstract class Equipable extends Entity {

	EquipState equippedState;

	EquipState unEquippedState;

	EquipState state = unEquippedState;

	public Equipable(int x, int y) {
		super(x, y);
		equippedState = new EquippedState();
		unEquippedState = new UnequippedState();
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

	@Override
	public abstract String getClassName();

}
