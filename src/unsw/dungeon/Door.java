package unsw.dungeon;

public class Door extends Entity implements Observer {
	private final int id;
	private DoorState state;

	public Door(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.id = dungeon.numDoor();
		this.state = new ClosedState();
	}

	public DoorState getState() {
		return this.state;
	}

	public int getId() {
		return this.id;
	}

	public void changeToOpenState() {
		this.state = new OpenState();
	}

	public void changeToCloseState() {
		// shouldn't do anything as open doors shouldn't be closed again
	}

	public boolean isOpen() {
		return this.state.shallPass();
	}

	@Override
	public void update(Subject obj) {
		Player player = (Player) obj;
		Key key = player.findKey();
		if (key == null) {
			return;
		}
		if (key.getId() == getId() && !isOpen()) {
			changeToOpenState();
			System.out.println("ID matches. Door opening");
			player.useKey(getId());
		}
	}

	@Override
	public String getClassName() {
		return "Door";
	}

}
