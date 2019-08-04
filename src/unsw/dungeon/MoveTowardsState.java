package unsw.dungeon;

import java.util.List;

/*
 * Move towards state of the enemy
 */
public class MoveTowardsState implements MoveState {

	/*
	 * change to MoveTowardsState
	 */
	@Override
	public MoveState transitionTowards() {
		return this;
	}

	/*
	 * change to MoveAwayState
	 */
	@Override
	public MoveState transitionAway() {
		return new MoveAwayState();
	}

	/*
	 * Get the director of the player.
	 */
	@Override
	public String getDirection(List<String> path) {
		String direction = path.get(path.size() - 1);
		path.remove(path.size() - 1);
		return direction;
	}

}
