package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import java.lang.Math;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

	// IntegerProperty is used so that changes to the entities position can be
	// externally observed.
	private IntegerProperty x, y;

	/**
	 * Create an entity positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Entity(int x, int y) {
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
	}

	public IntegerProperty x() {
		return x;
	}

	public IntegerProperty y() {
		return y;
	}

	public void setX(int x) {
		this.x.setValue(x);
	}

	public void setY(int y) {
		this.y.setValue(y);
	}

	public int getX() {
		return x().get();
	}

	/**
	 * checks if entity is next to a given location
	 * 
	 * @param x of the given location
	 * @param y of the given location
	 * @return true if adjacent to the grid
	 */
	public boolean adjacent(int x2, int y2) {
		if (getX() == x2 && Math.abs(getY() - y2) == 1) {
			return true;
		} else if (getY() == y2 && Math.abs(getX() - x2) == 1)
			return true;
		return true;
	}

	public int getY() {
		return y().get();
	}

	public abstract String getClassName();

}
