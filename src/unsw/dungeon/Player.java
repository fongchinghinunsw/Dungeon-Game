package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Movable implements Subject, Observer {

	private Dungeon dungeon;
	private Backpack backpack;
	private ArrayList<Observer> observers;
	private MoveSpeed moveSpeed;
	private boolean potionEffect;
	private boolean alive;

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
		this.alive = true;
	}

	/*
	 * Add the item into the backpack.
	 */
	public boolean equipItem() {
		ArrayList<Entity> entities = dungeon.getEntities(getX(), getY());
		for (Entity entity : entities) {
			if (entity instanceof Equipable) {
				Equipable e = (Equipable) entity;
				// player can only equip one sword at a time.
				if (entity instanceof Sword) {
					if (countSwordInBackPack() != 0) {
						return false;
					}
				}
				if (e.equip()) {
					backpack.addItem(e);
					System.out.println("Added item into the backpack");
					return true;
				}
			}
		}
		return false;
	}

	public boolean useItem(String type) {
		if (type.equals("Potion") && this.potionEffect) {
			System.out.println("A potion is already in use!");
			return false;
		}
		Equipable item = backpack.removeItem(type);
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
	
	public boolean isAlive() {
		return this.alive;
	}

	public boolean isInvincible() {
		return this.potionEffect;
	}

	public boolean die() {
		if (this.isInvincible()) {
			return false;
		}
		this.alive = false;
		System.out.println("You're dead lol");
		return true;
	}

	public void disablePotion() {
		this.potionEffect = false;
	}

	public int countSwordInBackPack() {
		return backpack.countSword();
	}

	public Entity removeSwordInBackPack() {
		return backpack.removeSword();
	}

	public long getSpeed() {
		return moveSpeed.getSpeed();
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

	/*
	 * Notify the current location of itself to all its observers, this method is
	 * called in DungeonController after each movement.
	 */
	@Override
	public void notifyObservers() {
		// this logic is to ensure the entity still exist on the map but not just in the
		// observers.
		if (dungeon.sameClass(getX(), getY(), "Key", "Exit", "Bomb", "Potion", "Treasure", "Sword", "Enemy")) {
			for (Observer o : observers) {
				Entity entity = (Entity) o;
				if (entity.getX() == getX() && entity.getY() == getY()) {
					o.update(this, dungeon);
				}
			}
		}
	}

	@Override
	public void update(Subject obj, Dungeon dungeon) {
		if (obj instanceof Enemy) {
			Enemy enemy = (Enemy) obj;
			if (countSwordInBackPack() > 0) {
				backpack.reduceSwordDurability();
				enemy.die();
			}
		}
	}

	@Override
	public String getClassName() {
		return "Player";
	}
}
