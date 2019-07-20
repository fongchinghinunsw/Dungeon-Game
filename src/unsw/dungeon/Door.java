package unsw.dungeon;

import java.util.ArrayList;

public class Door extends Entity implements Observer {
	private final int id;
	private DoorState state;

	public Door(int x, int y, int id) {
		super(x, y);
		this.id = id;
		this.state = new ClosedState();
	}

	public DoorState getState() {
		return this.state;
	}

	public int getId() {
		return this.id;
	}

	public OpenState getOpenState() {
		return new OpenState();
	}

	public ClosedState getClosedState() {
		return new ClosedState();
	}

	public void setState(DoorState newState) {
		this.state = newState;
	}

	@Override
	public String getClassName() {
		return "Door";
	}

	@Override
	public void update(Subject obj) {
		Player player = (Player) obj;
		ArrayList<Key> keys = player.findKeys();
		System.out.println("This line is executed.");
		for (Key key : keys) {
			if (key.getId() == this.getId()) {
				setState(getOpenState());
				System.out.println("ID matches. Door opening");
			}
		}
	}

	public boolean isOpen() {
		return this.state.shallPass();
	}

}
