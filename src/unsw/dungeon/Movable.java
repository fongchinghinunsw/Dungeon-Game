package unsw.dungeon;

/*
 * Movable class, all entities that can move are extended from this class.
 */
public abstract class Movable extends Entity {

	private Dungeon dungeon;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Movable(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	/*
	 * Move upward.
	 */
	public void moveUp() {
		if (getY() > 0 && !(dungeon.sameClass(getX(), getY() - 1, "Wall"))) {
			if (dungeon.canStepOn(getX(), getY() - 1) && dungeon.canPush(getX(), getY() - 1)) {
				y().set(getY() - 1);
			}
		}
	}

	/*
	 * Move downward.
	 */
	public void moveDown() {
		if (getY() < dungeon.getHeight() - 1 && !(dungeon.sameClass(getX(), getY() + 1, "Wall"))) {
			if (dungeon.canStepOn(getX(), getY() + 1) && dungeon.canPush(getX(), getY() + 1)) {
				y().set(getY() + 1);
			}
		}
	}

	/*
	 * Move leftward.
	 */
	public void moveLeft() {
		if (getX() > 0 && !(dungeon.sameClass(getX() - 1, getY(), "Wall"))) {
			if (dungeon.canStepOn(getX() - 1, getY()) && dungeon.canPush(getX() - 1, getY())) {
				x().set(getX() - 1);
			}
		}
	}

	/*
	 * Move rightward.
	 */
	public void moveRight() {
		if (getX() < dungeon.getWidth() - 1 && !(dungeon.sameClass(getX() + 1, getY(), "Wall"))) {
			if (dungeon.canStepOn(getX() + 1, getY()) && dungeon.canPush(getX() + 1, getY())) {
				x().set(getX() + 1);
			}
		}
	}

	/*
	 * Get the speed factor.
	 */
	public abstract long getSpeedFactor();

	/*
	 * Get the class name.
	 */
	@Override
	public abstract String getClassName();
}
