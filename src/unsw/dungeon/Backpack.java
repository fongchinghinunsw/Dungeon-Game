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
	
	public Equipable getItem(String type) {
		for (Equipable item:this.items) {
			if(item.getClassName().equals(type)) {
				this.items.remove(item);
				return item;
			}
		}
		return null;
	}

	public boolean addItem(Equipable item) {
		if (this.items.size() == max_size)
			return false;
		this.items.add(item);
		return true;
	}

}
