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

	public void setState(DoorState newState) {
		if (newState instanceof OpenState) {
			this.state = newState;
		}
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
			setState(getOpenState());
			System.out.println("ID matches. Door opening");
			player.useKey(getId());
		}
	}

	@Override
	public String getClassName() {
		return "Door";
	}

}
