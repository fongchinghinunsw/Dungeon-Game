package unsw.dungeon;

import java.util.List;

public class MoveTowardsState implements MoveState {

	@Override
	public MoveState transitionTowards() {
		return this;
	}

	@Override
	public MoveState transitionAway() {
		return new MoveAwayState();
	}

	@Override
	public String getDirection(List<String> path) {
		return path.get(0);
	}

}
