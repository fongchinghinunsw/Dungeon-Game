package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
	private MoveSpeed moveSpeed;
	private boolean potionEffect;

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
		this.moveSpeed = new Normal();
		this.potionEffect = false;
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

	// TODO: add useItem logic, now it just discards the item
	public boolean useItem(String type) {
		if (type.equals("Potion") && this.potionEffect) {
			System.out.println("A potion is already in use!");
			return false;
		}
		Equipable item = backpack.getItem(type);
		if (item == null) {
			String message = "You don't have an item of " + type + " kind!";
			System.out.println(message);
			return false;
		}
		// Temporary solution, open to modification
		if (type.equals("Potion")) {
			this.potionEffect = true;
			Timer timer = new Timer();
			PotionTimer task = new PotionTimer((Potion) item, this);
			timer.schedule(task, 0, 1000);
		}
		String message = "An item of type " + type + " is used.";
		System.out.println(message);
		return true;
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
		if (dungeon.sameClass(getX(), getY(), "Key", "Exit", "Bomb", "Potion")) {
			for (Observer o : observers) {
				Entity entity = (Entity) o;
				if (entity.getX() == getX() && entity.getY() == getY()) {
					o.update(this);
				}
			}
		}
	}

	@Override
	public String getClassName() {
		return "Player";
	}

	public long getSpeed() {
		return moveSpeed.getSpeed();
	}

	public void disablePotion() {
		this.potionEffect = false;
	}

	public void setMoveSpeed(MoveSpeed moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
}
