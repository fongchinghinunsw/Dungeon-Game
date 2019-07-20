package unsw.dungeon;

public class Key extends Equipable implements Observer {

	private final int id;

	public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String getClassName() {
		return "Key";
	}

	@Override
	public void update(Subject obj) {
		System.out.println("Holly crap, the player is now standing on me...");
	}

}
