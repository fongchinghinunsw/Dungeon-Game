package unsw.dungeon;

public class Key extends Equipable implements Observer {

	private int id;

	public Key(int x, int y) {
		super(x, y);
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
