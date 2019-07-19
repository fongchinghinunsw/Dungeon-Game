package unsw.dungeon;

import java.util.Timer;

public class Enemy extends Character implements Observer {

	private MoveSpeed moveSpeed;
	private Dungeon dungeon;
	private boolean alive;

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.dungeon = dungeon;
		this.moveSpeed = new Slow();
		this.alive = true;
		Timer timer = new Timer();
		EnemyTimer task = new EnemyTimer(this, dungeon.getPlayer());
		timer.schedule(task, 0, 1000 / moveSpeed.getSpeed());
	}

	public boolean isAlive() {
		return this.alive;
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

	public void findPlayer() {
		int playerX = this.dungeon.getPlayer().getX();
		int playerY = this.dungeon.getPlayer().getY();
		boolean tried = false;
		if (this.getY() == playerY) {
			if (this.getX() > playerX) {
				this.moveLeft();
				tried = true;
			} else if (this.getX() < playerX) {
				this.moveRight();
				tried = true;
			}
		} else if (this.getX() == playerX) {
			if (this.getY() > playerY) {
				this.moveUp();
				tried = true;
			} else if (this.getY() < playerY) {
				this.moveDown();
				tried = true;
			}
		}
		if (this.getX() == playerX && this.getY() == playerY) {
			this.dungeon.killPlayer();
		}
		if (tried)
			System.out.println("Trying to move towards the player.");
	}
}
