package unsw.dungeon;

public class Bomb extends Equipable implements Observer{
	
	public Bomb(int x, int y) {
		super(x, y);
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
