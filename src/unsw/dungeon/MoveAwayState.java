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
		return path.get(0);
	}

}
