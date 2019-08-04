package unsw.dungeon;

import java.util.List;

/*
 * Representation of the move away state of the enemy.
 */
public class MoveAwayState implements MoveState {

	/*
	 * change to MoveTowardsState
	 */
	@Override
	public MoveState transitionTowards() {
		return new MoveTowardsState();
	}

	/*
	 * change to MoveAwayState
	 */
	@Override
	public MoveState transitionAway() {
		return this;

	}

	/*
	 * Get the director of the player.
	 */
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
