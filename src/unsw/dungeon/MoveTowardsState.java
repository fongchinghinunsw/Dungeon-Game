package unsw.dungeon;

public class MoveTowardsState implements MoveState {

	@Override
	public int getDirection(int eX, int eY, int pX, int pY) {
		if (eX == pX) {
			if (eY < pY) {
				return DOWN;
			} else if (eY > pY) {
				return UP;
			}
		} else if (eY == pY) {
			if (eX < pX) {
				return RIGHT;
			} else if (eX > pX) {
				return LEFT;
			}
		}
		return -1;
	}

	@Override
	public MoveState transitionTowards() {
		return this;
	}

	@Override
	public MoveState transitionAway() {
		return new MoveAwayState();
	}

}
