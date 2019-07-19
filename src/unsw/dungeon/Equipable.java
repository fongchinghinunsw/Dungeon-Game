package unsw.dungeon;

public class Equipable extends Entity {
	// Does it need to have a name?
	private String name;

	public Equipable(int x, int y, String name) {
		super(x, y);
		this.name = name;
	}

	@Override
	public String getClassName() {
		return null;
	}

	// TODO: Add a method to use an item

}
