package unsw.dungeon;

import java.util.ArrayList;

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
	 * removes the given key from backpack
	 * 
	 * @param key to be removed
	 */
	public void removeKey(Key key) {
		this.items.remove(key);
		String message = "Key with id " + key.getId() + " removed from backpack.";
		System.out.println(message);
	}

	/**
	 * gets a sword from the backpack
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
	 * removes a sword from the backpack
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
		System.out.println(sword.getDurability());
		System.out.println(countSword());
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
	 * gets the keys in the backpack
	 * 
	 * @return arraylist of keys
	 */
	public ArrayList<Key> getKeys() {
		ArrayList<Key> keys = new ArrayList<Key>();
		for (Equipable e : this.items) {
			if (e.getClassName().equals("Key")) {
				keys.add((Key) e);
			}
		}
		return keys;
	}
}
