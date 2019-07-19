package unsw.dungeon;

public class Potion extends Equipable implements Observer {

	private boolean inEffect;

	public Potion(int x, int y) {
		super(x, y);
		this.inEffect = false;
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
