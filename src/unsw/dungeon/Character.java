package unsw.dungeon;

public class Character extends Entity {

	private Dungeon dungeon;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Character(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	public void moveUp() {
		if (getY() > 0 && !(dungeon.hasWall(getX(), getY() - 1)))
			y().set(getY() - 1);
	}

	public void moveDown() {
		if (getY() < dungeon.getHeight() - 1 && !(dungeon.hasWall(getX(), getY() + 1)))
			y().set(getY() + 1);
	}

	public void moveLeft() {
		if (getX() > 0 && !(dungeon.hasWall(getX() - 1, getY())))
			x().set(getX() - 1);
	}

	public void moveRight() {
		if (getX() < dungeon.getWidth() - 1 && !(dungeon.hasWall(getX() + 1, getY())))
			x().set(getX() + 1);
	}
}
