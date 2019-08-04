package unsw.dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

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
	private BooleanProperty alive;

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
		this.alive = new SimpleBooleanProperty(true);
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
				if (entity instanceof Key) {
					if (hasKey()) {
						return false;
					}
				}
				if (e.equip()) {
					dungeon.removeObserver((Observer) e);
					dungeon.removeEntity(e);
					backpack.addItem(e);
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Check if the player has a key.
	 */
	private boolean hasKey() {
		return (backpack.getKey() != null);
	}

	/*
	 * Use an item in the player's backpack.
	 */
	public boolean useItem(String type) {
		// a potion is already in use.
		if (type.equals("Potion") && this.potionEffect) {
			return false;
		}
		Equipable item = backpack.removeItem(type);
		if (item == null) {
			return false;
		}
		if (type.equals("Potion")) {
			this.potionEffect = true;
			Potion potion = (Potion) item;
			potion.usePotion();
		}

		return true;
	}

	/*
	 * Check if the player is alive.
	 */
	public BooleanProperty isAlive() {
		return this.alive;
	}

	/*
	 * Check if the player is invincible.
	 */
	public boolean isInvincible() {
		return this.potionEffect;
	}

	/*
	 * Set the player as dead.
	 */
	public void die() {
		this.alive.setValue(false);
	}

	/*
	 * Set the potion effect on the player as false.
	 */
	public void disablePotion() {
		this.potionEffect = false;
	}

	/*
	 * Get the bomb from the player's backpack.
	 */
	public Bomb getBomb() {
		return backpack.getBomb();
	}

	/*
	 * Remove the key from the backpack.
	 */
	public Key removeKeyInBackpack() {
		return backpack.removeKey();
	}

	/*
	 * Count number of sword in the backpack.
	 */
	public int countSwordInBackPack() {
		return backpack.countSword();
	}

	/*
	 * Remove sword in the backpack.
	 */
	public Entity removeSwordInBackPack() {
		return backpack.removeSword();
	}

	/*
	 * Remove bomb in the backpack.
	 */
	public Entity removeBombInBackpack() {
		return backpack.removeBomb();
	}

	/*
	 * Get speed factor of the player.
	 */
	public long getSpeedFactor() {
		return moveSpeed.getSpeedFactor();
	}

	/*
	 * Get the key in the backpack.
	 */
	public Key findKey() {
		return backpack.getKey();
	}

	/*
	 * Use the key.
	 */
	public void useKey(int id) {
		Key key = this.findKey();
		if (key.getId() == id) {
			this.backpack.removeKey();
		}
	}

	/*
	 * Get the backpack.
	 */
	public Backpack getBag() {
		return backpack;
	}

	/*
	 * Count number of different items in the backpack.
	 */
	public Map<String, Integer> getNumberOfItemsInBackpack() {
		return backpack.getNumberOfItems();
	}

	/*
	 * Attach an observer.
	 */
	@Override
	public void attach(Observer o) {
		if (!(observers.contains(o))) {
			observers.add(o);
		}
	}

	/*
	 * Detach an observer.
	 */
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
		Set<String> notifySet = new HashSet<String>(
				Arrays.asList("Key", "Exit", "Bomb", "Potion", "Treasure", "Sword", "Enemy", "Door", "Boulder"));
		if (alive.getValue() && dungeon.sameClass(getX(), getY(), notifySet)) {
			for (Observer o : observers) {
				Entity entity = (Entity) o;
				if (entity.getX() == getX() && entity.getY() == getY()) {
					o.update(this);
				}
			}
		}
		if (!(dungeon.sameClass(getX(), getY(), "Enemy"))) {
			for (Observer o : observers) {
				// notify enemy when the player make any moves.
				if (o instanceof Enemy) {
					o.update(this);
				}
			}
		}
		for (Observer o : observers) {
			Entity entity = (Entity) o;
			if (entity.adjacent(getX(), getY()) && entity.getClassName().equals("Door")) {
				o.update(this);
			}
			if (entity.adjacent(getX(), getY()) && entity.getClassName().equals("Boulder")) {
				o.update(this);
			}
		}
	}

	/*
	 * If the player meet the enemy and has a sword, then it kills the enemy, else
	 * do nothing and the enemy will kill him.
	 */
	@Override
	public void update(Subject obj) {
		if (obj instanceof Enemy) {
			Enemy enemy = (Enemy) obj;

			if (isInvincible()) {
				dungeon.killEnemy(enemy);
			} else if (countSwordInBackPack() > 0) {
				backpack.reduceSwordDurability();
				dungeon.killEnemy(enemy);
			} else {
				this.notifyObservers();
			}
		} else if (obj instanceof Bomb) {
			if (!this.potionEffect) {
				die();
			}
		}
	}

	/*
	 * Get the class name of the player.
	 */
	@Override
	public String getClassName() {
		return "Player";
	}
}
