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
		this.durability--;
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