package unsw.dungeon;

/**
 * Wall entity of the dungeon.
 *
 * A basic prototype to serve as the representation of a wall.
 *
 * @author Stephen Fong
 *
 */
public class Wall extends Entity {

	/*
	 * Constructor of the wall.
	 */
	public Wall(int x, int y) {
		super(x, y);
	}

	/*
	 * Get the class name of the wall.
	 */
	@Override
	public String getClassName() {
		return "Wall";
	}
}
