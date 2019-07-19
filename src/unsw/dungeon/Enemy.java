package unsw.dungeon;

public class Enemy extends Character implements Observer {

	private MoveSpeed moveSpeed;
	private Dungeon dungeon;

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.dungeon = dungeon;
		this.moveSpeed = new Slow();
	}

	@Override
	public void update(Subject obj) {
		System.out.println("Attempting to attack the player......");
	}

	public long getSpeed() {
		return moveSpeed.getSpeed();
	}

	@Override
	public String getClassName() {
		return "Enemy";
	}

}
