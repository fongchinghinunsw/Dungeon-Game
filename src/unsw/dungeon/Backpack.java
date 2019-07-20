package unsw.dungeon;

import java.util.ArrayList;

public class Backpack {
	private ArrayList<Equipable> items;
	private int max_size;

	public Backpack() {
		this.items = new ArrayList<Equipable>();
		// maximum size set to 9 for now
		this.max_size = 9;
	}

	public boolean addItem(Equipable item) {
		if (this.items.size() == max_size)
			return false;
		this.items.add(item);
		return true;
	}

	public Equipable removeItem(String type) {
		for (Equipable item : items) {
			if (item.getClassName().equals(type)) {
				items.remove(item);
				return item;
			}
		}
		return null;
	}
	
	public void removeKey(Key key) {
		this.items.remove(key);
		String message = "Key with id "+key.getId()+" removed from backpack.";
		System.out.println(message);
	}

	public Sword getSword() {
		for (Equipable entity : items) {
			if (entity.getClassName().equals("Sword")) {
				return (Sword) entity;
			}
		}
		return null;
	}

	public Sword removeSword() {
		Sword entity = getSword();
		if (entity != null) {
			entity.unequip();
			items.remove(entity);
			return entity;
		}
		return null;
	}

	public void reduceSwordDurability() {
		Sword sword = getSword();
		sword.reduceDurability();
		System.out.println(sword.getDurability());
		System.out.println(countSword());
		if (sword.getDurability() == 0) {
			items.remove(sword);
		}
	}

	public int countSword() {
		int count = 0;
		for (Equipable item : items) {
			if (item instanceof Sword) {
				count++;
			}
		}
		return count;
	}

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
