package unsw.dungeon;

public class Sword extends Equipable implements Observer {

	private int durability;

	public Sword(int x, int y) {
		super(x, y);
		this.durability = 5;
	}

	public int getDurability() {
		return this.durability;
	}

	public void reduceDurability() {
		if (durability > 0) {
			this.durability--;
		}
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
	public void update(Subject obj) {
		System.out.println("You see a sword.");
	}

	@Override
	public String getClassName() {
		return "Sword";
	}

}
