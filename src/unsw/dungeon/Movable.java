package unsw.dungeon;

public abstract class Movable extends Entity {

	private Dungeon dungeon;

	/**
	 * Create a movable object positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Movable(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	public void moveUp() {
		if (getY() > 0 && !(dungeon.sameClass(getX(), getY() - 1, "Wall")))
			y().set(getY() - 1);
	}

	public void moveDown() {
		if (getY() < dungeon.getHeight() - 1 && !(dungeon.sameClass(getX(), getY() + 1, "Wall")))
			y().set(getY() + 1);
	}

	public void moveLeft() {
		if (getX() > 0 && !(dungeon.sameClass(getX() - 1, getY(), "Wall")))
			x().set(getX() - 1);
	}

	public void moveRight() {
		if (getX() < dungeon.getWidth() - 1 && !(dungeon.sameClass(getX() + 1, getY(), "Wall")))
			x().set(getX() + 1);
	}

	public abstract long getSpeed();

	@Override
	public abstract String getClassName();
}
