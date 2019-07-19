package unsw.dungeon;

public class Treasure extends Equipable implements Observer {
	public Treasure(int x, int y) {
		super(x, y);
	}

	@Override
	public String getClassName() {
		return "Treasure";
	}

	@Override
	public void update(Subject obj, Dungeon dungeon) {
		System.out.println("You are standing on a treasure");
	}
}
