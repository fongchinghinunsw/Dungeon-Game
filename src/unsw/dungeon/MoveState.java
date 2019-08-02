package unsw.dungeon;

import java.util.List;

public interface MoveState {

	final int LEFT = 0;
	final int RIGHT = 1;
	final int UP = 2;
	final int DOWN = 3;

	public abstract MoveState transitionTowards();

	public abstract MoveState transitionAway();

	public abstract String getDirection(List<String> path);

}
