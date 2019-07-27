package unsw.dungeon;

public interface MoveState {

	final int LEFT = 0;
	final int RIGHT = 1;
	final int UP = 2;
	final int DOWN = 3;

	public abstract MoveState transitionTowards();

	public abstract MoveState transitionAway();

	public abstract int getDirection(int eX, int eY, int pX, int pY);

}
