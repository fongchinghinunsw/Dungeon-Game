package unsw.dungeon;

public class Switch extends Entity implements Observer {
	public Switch(int x, int y) {
		super(x, y);
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
