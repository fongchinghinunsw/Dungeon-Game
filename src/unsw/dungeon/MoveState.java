package unsw.dungeon;

import java.util.List;

public interface MoveState {

	public abstract MoveState transitionTowards();

	public abstract MoveState transitionAway();

	public abstract String getDirection(List<String> path);

}
