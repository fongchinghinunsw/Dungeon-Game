package unsw.dungeon;

public class Switch extends Entity implements Observer {

	private Dungeon dungeon;

	public Switch(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	@Override
	public void update(Subject obj) {

	}

	@Override
	public String getClassName() {
		return "Switch";
	}
}
