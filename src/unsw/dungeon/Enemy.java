package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;

public class Enemy extends Character implements Subject, Observer {

	private MoveSpeed moveSpeed;
	private Dungeon dungeon;
	private ArrayList<Observer> observers;
	private boolean alive;

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.dungeon = dungeon;
		this.moveSpeed = new Slow();
		this.alive = true;
		this.observers = new ArrayList<>();
		Timer timer = new Timer();
		EnemyTimer task = new EnemyTimer(this, dungeon.getPlayer());
		timer.schedule(task, 0, 1000 / moveSpeed.getSpeed());
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void die() {
		this.alive = false;
		System.out.println("Enemy is dead");
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
		System.out.println("Enemy meets u");

	}

	@Override
	public void attach(Observer o) {
		if (!(observers.contains(o))) {
			observers.add(o);
		}
	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		if (dungeon.sameClass(getX(), getY(), "Player")) {
			for (Observer o : observers) {
				o.update(this, dungeon);
			}
		}
	}
}
