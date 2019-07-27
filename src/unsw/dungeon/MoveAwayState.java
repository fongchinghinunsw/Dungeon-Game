package unsw.dungeon;

public class MoveAwayState implements MoveState {

	@Override
	public int getDirection(int eX, int eY, int pX, int pY) {
		if (eX == pX) {
			if (eY < pY) {
				return UP;
			} else if (eY > pY) {
				return DOWN;
			}
		} else if (eY == pY) {
			if (eX < pX) {
				return LEFT;
			} else if (eX > pX) {
				return RIGHT;
			}
		}
		return -1;
	}

	@Override
	public MoveState transitionTowards() {
		return new MoveTowardsState();
	}

	@Override
	public MoveState transitionAway() {
		return this;

	}

}
