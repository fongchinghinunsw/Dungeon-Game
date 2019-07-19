package unsw.dungeon;

public class Potion extends Equipable implements Observer {

	public Potion(int x, int y) {
		super(x, y);
	}

	@Override
	public String getClassName() {
		return "Potion";
	}

	@Override
	public void update(Subject obj) {
		System.out.println("Player standing on a potion");
	}
}
