package unsw.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Backpack {

	private ArrayList<Equipable> items;
	private int max_size;

	/**
	 * constructor for backpack class
	 */
	public Backpack() {
		this.items = new ArrayList<Equipable>();
		// maximum size set to 9 for now
		this.max_size = 9;
	}

	/**
	 * adds an item to the backpack
	 * 
	 * @param item to be added
	 * @return if successfully added
	 */
	public boolean addItem(Equipable item) {
		if (this.items.size() == max_size)
			return false;
		this.items.add(item);
		return true;
	}

	/**
	 * remove an item of the given type
	 * 
	 * @param type of item to remove
	 * @return removed item
	 */
	public Equipable removeItem(String type) {
		for (Equipable item : items) {
			if (item.getClassName().equals(type)) {
				items.remove(item);
				return item;
			}
		}
		return null;
	}

	/**
	 * gets the key in the backpack
	 * 
	 * @return key in bag
	 */
	public Key getKey() {
		for (Equipable e : this.items) {
			if (e.getClassName().equals("Key")) {
				return (Key) e;
			}
		}
		return null;
	}

	/**
	 * removes a key from the bag
	 * 
	 * @return the key removed
	 */
	public Key removeKey() {
		for (Equipable e : items) {
			if (e.getClassName().equals("Key")) {
				Key key = (Key) e;
				String message = "Key with id " + key.getId() + " removed from backpack.";
				e.unequip();
				items.remove(e);
				System.out.println(message);
				return key;
			}
		}
		return null;
	}

	/**
	 * gets the bomb in the backpack
	 * 
	 * @return bomb in bag
	 */
	public Bomb getBomb() {
		for (Equipable e : items) {
			if (e.getClassName().equals("Bomb")) {
				return (Bomb) e;
			}
		}
		return null;
	}

	/**
	 * removes a bomb from the backpack (doesn't matter which one)
	 * 
	 * @return bomb removed
	 */
	public Bomb removeBomb() {
		Bomb entity = getBomb();
		if (entity != null) {
			entity.unequip();
			items.remove(entity);
			return entity;
		}
		return null;
	}

	/**
	 * gets the sword from the backpack
	 * 
	 * @return the sword
	 */
	public Sword getSword() {
		for (Equipable entity : items) {
			if (entity.getClassName().equals("Sword")) {
				return (Sword) entity;
			}
		}
		return null;
	}

	/**
	 * removes the sword from the backpack
	 * 
	 * @return removed sword
	 */
	public Sword removeSword() {
		Sword entity = getSword();
		if (entity != null) {
			entity.unequip();
			items.remove(entity);
			return entity;
		}
		return null;
	}

	/**
	 * wears off the sword when its used
	 */
	public void reduceSwordDurability() {
		Sword sword = getSword();
		sword.reduceDurability();
		System.out.printf("Sword's durability is %d\n", sword.getDurability());
		if (sword.getDurability() == 0) {
			items.remove(sword);
		}
	}

	/**
	 * counts the number of swords in backpack
	 * 
	 * @return number of swords
	 */
	public int countSword() {
		int count = 0;
		for (Equipable item : items) {
			if (item instanceof Sword) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Counts the number of each item in the bag
	 * 
	 * @return mapping of items to their respective numbers
	 */
	public Map<String, Integer> getNumberOfItems() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Equipable item : items) {
			if (map.containsKey(item.getClassName())) {
				map.put(item.getClassName(), map.get(item.getClassName()) + 1);
			} else {
				map.put(item.getClassName(), 1);
			}
		}
		return map;
	}
}
