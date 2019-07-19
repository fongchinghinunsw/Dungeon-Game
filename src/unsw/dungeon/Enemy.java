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

	public long getSpeed() {
		return moveSpeed.getSpeed();
	}

	@Override
	public String getClassName() {
		return "Enemy";
	}

	public void findPlayer() {
		Player player = this.dungeon.getPlayer();
		int playerX = player.getX();
		int playerY = player.getY();
		if (this.getY() == playerY) {
			if (this.getX() > playerX) {
				if (player.isInvincible()) {
					this.moveRight();
				} else {
					this.moveLeft();
				}
			} else if (this.getX() < playerX) {
				if (player.isInvincible()) {
					this.moveLeft();
				} else {
					this.moveRight();
				}
			}
		} else if (this.getX() == playerX) {
			if (this.getY() > playerY) {
				if (player.isInvincible()) {
					this.moveDown();
				} else {
					this.moveUp();
				}
			} else if (this.getY() < playerY) {
				if (player.isInvincible()) {
					this.moveUp();
				} else {
					this.moveDown();
				}
			}
		}
		if (this.getX() == playerX && this.getY() == playerY) {
			this.dungeon.killPlayer();
		}
	}

	@Override
	public void update(Subject obj, Dungeon dungeon) {
		System.out.println("Enemy is doing something");
		
	}
}
