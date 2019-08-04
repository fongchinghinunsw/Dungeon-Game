package unsw.dungeon;

/*
 * Open state of the door.
 */
public class OpenState implements DoorState {

	/*
	 * return true as the door is opened
	 */
	public boolean shallPass() {
		return true;
	}
}
