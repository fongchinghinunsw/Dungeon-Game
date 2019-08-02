package unsw.dungeon;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;

public abstract class Enemy extends Movable implements Subject, Observer {

	private Dungeon dungeon;
	private MoveSpeed moveSpeed;
	private ArrayList<Observer> observers;
	private BooleanProperty alive;
	private MoveState moveState;
	private Timeline enemyTimer;
	private int timerTick;

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.dungeon = dungeon;
		this.timerTick = 0;
		this.moveSpeed = new Slow();
		this.alive = new SimpleBooleanProperty(true);
		this.observers = new ArrayList<>();
		this.moveState = new MoveTowardsState();

		dungeon.towardsPlayerPath(this.getX(), this.getY(), dungeon.getPlayerX(), dungeon.getPlayerY());

		this.enemyTimer = new Timeline(new KeyFrame(Duration.seconds(0.01), e -> {
			// the higher the speed is the more frequent the enemy moves
			if (timerTick * this.moveSpeed.getSpeed() % 100 == 0) {
				this.findPlayer();
			}
			this.notifyObservers();
			this.timerTick++;
		}));
		enemyTimer.setCycleCount(Timeline.INDEFINITE);
		enemyTimer.play();
	}

	public void setSpeed(MoveSpeed newSpeed) {
		this.moveSpeed = newSpeed;
	}

	public BooleanProperty isAlive() {
		return this.alive;
	}

	public void die() {
		alive.set(false);
		System.out.println("Enemy is dead");
	}

	public void findPlayer() {
		if (!alive.getValue()) {
			return;
		}
//		System.out.println("Trying to find the player......");
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
//		this.notifyObservers();
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
		if (!this.isAlive().getValue()) {
			return;
		}
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
