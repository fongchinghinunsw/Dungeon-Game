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

	}

	@Override
	public String getClassName() {
		return "Switch";
	}
}
