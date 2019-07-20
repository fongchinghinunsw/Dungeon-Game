package unsw.dungeon;

public class Switch extends Entity implements Observer {

	private Dungeon dungeon;
	private boolean triggered;

	public Switch(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.triggered = false;
	}

	@Override
	public void update(Subject obj) {
		// observing from boulders.
		System.out.println("I'm triggered");

	}

	@Override
	public String getClassName() {
		return "Switch";
	}
}
