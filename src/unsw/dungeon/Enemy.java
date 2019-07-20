package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Enemy extends Movable implements Subject, Observer {

	private Dungeon dungeon;
	private MoveSpeed moveSpeed;
	private ArrayList<Observer> observers;
	private BooleanProperty alive;

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.dungeon = dungeon;
		this.moveSpeed = new Slow();
		this.alive = new SimpleBooleanProperty(true);
		this.observers = new ArrayList<>();
		Timer timer = new Timer();
		EnemyTimer task = new EnemyTimer(this, dungeon.getPlayer());
		timer.schedule(task, 0, 1000 / moveSpeed.getSpeed());
	}

	public BooleanProperty isAlive() {
		return this.alive;
	}

	public void die() {
		alive.set(false);
		System.out.println("Enemy is dead");
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
	}

	public long getSpeed() {
		return moveSpeed.getSpeed();
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
				if (o instanceof Player) {
					o.update(this);
				}

			}
		}
	}

	/*
	 * THe enemy will get updated from the player when the player is on the same
	 * grid with it.
	 */
	@Override
	public void update(Subject obj) {
		if (obj instanceof Player) {
			Player player = (Player) obj;
			if (player.countSwordInBackPack() == 0 && !player.isInvincible()) {
				dungeon.killPlayer();
			}
		}
	}

	@Override
	public String getClassName() {
		return "Enemy";
	}
}
