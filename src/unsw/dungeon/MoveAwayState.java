package unsw.dungeon;

import java.util.List;

public class MoveAwayState implements MoveState {

	@Override
	public MoveState transitionTowards() {
		return new MoveTowardsState();
	}

	@Override
	public MoveState transitionAway() {
		return this;

	}

	@Override
	public String getDirection(List<String> path) {

		String nextMove = path.get(path.size() - 1);
		if (nextMove.equals("TOP")) {
			return "DOWN";
		} else if (nextMove.equals("DOWN")) {
			return "TOP";
		} else if (nextMove.equals("LEFT")) {
			return "RIGHT";
		} else if (nextMove.equals("RIGHT")) {
			return "LEFT";
		}
		return "";
	}

}
