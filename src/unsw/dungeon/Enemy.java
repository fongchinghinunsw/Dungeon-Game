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
	private MoveState moveState;

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.dungeon = dungeon;
		this.moveSpeed = new Slow();
		this.alive = new SimpleBooleanProperty(true);
		this.observers = new ArrayList<>();
		Timer timer = new Timer();
		EnemyTimer task = new EnemyTimer(dungeon, this);
		timer.schedule(task, 0, 100 / moveSpeed.getSpeed());
		this.moveState = new MoveTowardsState();
	}

	public BooleanProperty isAlive() {
		return this.alive;
	}

	public void die() {
		alive.set(false);
		System.out.println("Enemy is dead");
	}

	public void findPlayer() {
		dungeon.towardsPlayerPath(this.getX(), this.getY(), dungeon.getPlayerX(), dungeon.getPlayerY());
		Player player = this.dungeon.getPlayer();
		MoveState newState;
		if (player.isInvincible()) {
			newState = this.moveState.transitionAway();
		} else {
			newState = this.moveState.transitionTowards();
		}
		this.moveState = newState;
		int pX = player.getX();
		int pY = player.getY();
		int direction = this.moveState.getDirection(getX(), getY(), pX, pY);
		switch (direction) {
		case MoveState.LEFT:
			moveLeft();
			break;
		case MoveState.RIGHT:
			moveRight();
			break;
		case MoveState.UP:
			moveUp();
			break;
		case MoveState.DOWN:
			moveDown();
			break;
		default:
			break;
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
		Player p = dungeon.getPlayer();
		if (this.samePlace(p.getX(), p.getY())) {
			p.update(this);
		}
	}

	/*
	 * THe enemy will get updated from the player when the player is on the same
	 * grid with it.
	 */
	@Override
	public void update(Subject obj) {
		if (!this.isAlive().getValue()) {
			return;
		}

		if (obj instanceof Player) {
			Player player = (Player) obj;
			if (player.countSwordInBackPack() == 0 && !player.isInvincible()) {
				dungeon.killPlayer();
			} else {
				die();
			}
		}
		if (obj instanceof Bomb) {
			die();
		}
	}

	@Override
	public String getClassName() {
		return "Enemy";
	}
}
