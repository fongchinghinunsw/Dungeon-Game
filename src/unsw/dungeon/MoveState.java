package unsw.dungeon;

import java.util.List;

/*
 * Representation of the Move State of the enemy.
 */
public interface MoveState {

	/*
	 * change to MoveTowardsState
	 */
	public abstract MoveState transitionTowards();

	/*
	 * change to MoveAwayState
	 */
	public abstract MoveState transitionAway();

	/*
	 * Get the director of the player.
	 */
	public abstract String getDirection(List<String> path);

}
