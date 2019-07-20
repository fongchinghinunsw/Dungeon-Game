package unsw.dungeon;

public class ClosedState implements DoorState {

	@Override
	public void standOn() {

	}

	public boolean shallPass() {
		return false;
	}
}