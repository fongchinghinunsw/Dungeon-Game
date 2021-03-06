package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Boulder extends Movable implements Subject, Observer {

	private Dungeon dungeon;
	private ArrayList<Observer> observers;
	private int lastX, lastY;
	private BooleanProperty destroyed;

	/**
	 * constructor for boulder class
	 * 
	 * @param dungeon the boulder is in
	 * @param x       coordinate of the boulder
	 * @param y       coordinate of the boulder
	 */
	public Boulder(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.dungeon = dungeon;
		this.observers = new ArrayList<Observer>();
		this.lastX = dungeon.getPlayerX();
		this.lastY = dungeon.getPlayerY();
		destroyed = new SimpleBooleanProperty(false);
	}

	/**
	 * checks if boulder is destroyed
	 * @return true BoolPpty if destroyed
	 */
	public BooleanProperty destroyed() {
		return destroyed;
	}

	@Override
	public void attach(Observer o) {
		if (!(observers.contains(o))) {
			observers.add(o);
		}
	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {

	}

	@Override
	public void update(Subject obj) {
		if (obj instanceof Player) {
			// the player is on the boulder (temporary)
			if (dungeon.sameClass(getX(), getY(), "Player")) {

				if (lastX < getX() && lastY == getY()) {
					// the player is pushing from the left side.
					moveRight();
				} else if (lastX > getX() && lastY == getY()) {
					// the player is pushing from the right side.
					moveLeft();
				} else if (lastX == getX() && lastY < getY()) {
					// the player is pushing from the top side.
					moveDown();
				} else if (lastX == getX() && lastY > getY()) {
					// the player is pushing from the down side.
					moveUp();
				}
			} else {
				Player player = (Player) obj;
				lastX = player.getX();
				lastY = player.getY();
			}
		} else if (obj instanceof Bomb) {
			dungeon.destroyBoulder(this);
			destroyed.set(true);
		}
	}

	@Override
	public long getSpeedFactor() {
		return 0;
	}

	@Override
	public String getClassName() {
		return "Boulder";
	}

}
