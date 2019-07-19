package unsw.dungeon;

public class Potion extends Equipable implements Observer {
	
	private int countdownTime;
	
	public Potion(int x, int y) {
		super(x, y);
		countdownTime = 5;
	}

	public int getTime() {
		return this.countdownTime;
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
