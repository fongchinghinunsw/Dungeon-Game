package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity implements Observer {

	private final int id;
	private DoorState state;
	BooleanProperty open;

	/**
	 * Constructor for door class
	 * 
	 * @param dungeon
	 * @param x       coordinate
	 * @param y       coordinate
	 */
	public Door(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.id = dungeon.numDoor();
		this.state = new ClosedState();
		open = new SimpleBooleanProperty(false);
	}

	/**
	 * gets the state of this door
	 * 
	 * @return state
	 */
	public DoorState getState() {
		return this.state;
	}

	/**
	 * gets the id of this door
	 * 
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * gets the open BooleanProperty for the door this attribute is used for image
	 * removal(frontend)
	 * 
	 * @return open attribute
	 */
	public BooleanProperty getOpen() {
		return this.open;
	}

	/**
	 * changes DoorState to open state
	 */
	public void changeToOpenState() {
		this.state = new OpenState();
		open.setValue(true);
	}

	/**
	 * changes DoorState to close state
	 */
	public void changeToCloseState() {
		// shouldn't do anything as open doors shouldn't be closed again
	}

	/**
	 * checks if door is open this is for backend checking
	 * 
	 * @return true if door is open
	 */
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
			player.useKey(getId());
		}
	}

	@Override
	public String getClassName() {
		return "Door";
	}

}
