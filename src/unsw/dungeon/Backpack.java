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

	public Entity removeItem(String className) {
		for (Entity entity : items) {
			if (entity.getClassName().equals(className)) {
				Entity tmp = entity;
				items.remove(entity);
				return tmp;
			}
		}
		return null;
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
}
