package unsw.dungeon;

public class Exit extends Entity implements Observer {

	public Exit(int x, int y) {
		super(x, y);
	}

	@Override
	public String getClassName() {
		return "Exit";
	}

	@Override
	public void update(Subject obj) {
		System.out.println("You are standing on the exit.");

	}

}
