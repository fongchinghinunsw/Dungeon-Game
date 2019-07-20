package unsw.dungeon;

public class Bomb extends Equipable implements Observer {

	private int countdownTime;
	private boolean exploded;
	
	public Bomb(int x, int y) {
		super(x, y);
		countdownTime = 3;
		exploded = false;
	}

	public int getTime() {
		return countdownTime;
	}
	
	public boolean exploded() {
		return exploded;
	}
	
	public void explode() {
		exploded = true;
	}

	@Override
	public String getClassName() {
		return "Bomb";
	}

	@Override
	public void update(Subject obj) {
		System.out.println("Player standing on a bomb");
	}

}
