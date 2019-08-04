package unsw.dungeon;

public class ClosedState implements DoorState {

	/**
	 * Checks if the player can pass
	 */
	public boolean shallPass() {
		return false;
	}
}
