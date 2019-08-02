package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

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
	private List<String> pathToPlayer;

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.dungeon = dungeon;
		this.timerTick = 0;
		this.moveSpeed = new Slow();
		this.alive = new SimpleBooleanProperty(true);
		this.observers = new ArrayList<>();
		this.moveState = new MoveTowardsState();
		this.pathToPlayer = null;

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
		if (this.pathToPlayer == null) {
			this.pathToPlayer = dungeon.towardsPlayerPath(this.getX(), this.getY(), dungeon.getPlayerX(),
					dungeon.getPlayerY());
		}

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

		String direction;
		if (pathToPlayer.size() > 0) {
			direction = this.moveState.getDirection(this.pathToPlayer);
		} else {
			System.out.println("No more direction");
			direction = "";
		}

		if (direction.equals("LEFT")) {
			moveLeft();
		} else if (direction.equals("RIGHT")) {
			moveRight();
		} else if (direction.equals("UP")) {
			moveUp();
		} else if (direction.equals("DOWN")) {
			moveDown();
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
			if (dungeon.sameClass(getX(), getY(), "Player")) {
				if (player.countSwordInBackPack() == 0 && !player.isInvincible()) {
					dungeon.killPlayer();
				}
			} else {
				System.out.println("Get notified");
				// regenerate the path once the player moves.
				this.pathToPlayer = dungeon.towardsPlayerPath(getX(), getY(), player.getX(), player.getY());
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
