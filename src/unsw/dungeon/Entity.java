package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
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

	/*
	 * Get the value of x
	 */
	public IntegerProperty x() {
		return x;
	}

	/*
	 * Get the value of y
	 */
	public IntegerProperty y() {
		return y;
	}

	/*
	 * Set the value of x
	 */
	public void setX(int x) {
		this.x.setValue(x);
	}

	/*
	 * Set the value of y
	 */
	public void setY(int y) {
		this.y.setValue(y);
	}

	/*
	 * Get integer value of x
	 */
	public int getX() {
		return x.get();
	}

	/*
	 * Get integer value of y.
	 */
	public int getY() {
		return y.get();
	}

	/*
	 * Get the name of the class
	 */
	public abstract String getClassName();

}
