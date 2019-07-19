package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Character implements Subject {

	private Dungeon dungeon;
	private Backpack backpack;
	private ArrayList<Observer> observers;
	MoveSpeed speed;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x x-coordinate of the player
	 * @param y y-coordinate of the player
	 */
	public Player(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.dungeon = dungeon;
		this.backpack = new Backpack();
		this.observers = new ArrayList<Observer>();
		this.speed = new Normal();
	}

	@Override
	public String getClassName() {
		return "Player";

	}

	// Here for now, open to modification/deletion
	public Backpack getBackpack() {
		return this.backpack;
	}

	public boolean equipItem() {
		ArrayList<Entity> entities = dungeon.getEntity(getX(), getY());
		for (Entity entity : entities) {
			if (entity instanceof Equipable) {
				Equipable e = (Equipable) entity;
				if (e.equip()) {
					backpack.addItem(e);
					System.out.println("Added item into the backpack");
					return true;
				}
			}
		}
		return false;
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
		if (dungeon.sameClass(getX(), getY(), "Key", "Exit")) {
			for (Observer o : observers) {
				Entity entity = (Entity) o;
				if (entity.getX() == getX() && entity.getY() == getY()) {
					o.update(this);
				}
			}
		}
	}
	public int getSpeed() {
		return this.speed.getSpeed();
	}
}
