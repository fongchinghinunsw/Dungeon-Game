package unsw.dungeon;

public class Sword extends Equipable implements Observer {

	public Sword(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean equip() {
		if (state.equip()) {
			setState(equippedState);
			return true;
		}
		return false;
	}

	@Override
	public String getClassName() {
		return "Sword";
	}

	@Override
	public void update(Subject obj, Dungeon dungeon) {
		System.out.println("You see a sword.");
	}

}
