package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

/**
 * Potion entity of the dungeon.
 *
 * A basic prototype to serve as the representation of a potion.
 *
 * @author Stephen Fong
 *
 */
public class Potion extends Equipable implements Observer, Runnable {

	Dungeon dungeon;
	private IntegerProperty countdownTime;
	private BooleanProperty pause;
	private Timeline potionTimer;

	/*
	 * Constructor of a potion.
	 */
	public Potion(Dungeon dungeon, int x, int y) {
		super(x, y);
		countdownTime = new SimpleIntegerProperty(5);
		pause = new SimpleBooleanProperty();
		this.potionTimer = createTimer(countdownTime.getValue());
		this.dungeon = dungeon;
	}

	/*
	 * Get the remaining time of the potion timer.
	 */
	public IntegerProperty getTime() {
		return this.countdownTime;
	}

	/*
	 * Decrement the remaining time of the timer.
	 */
	public void decrementCountdownTime() {
		countdownTime.setValue(countdownTime.getValue() - 1);
	}

	/*
	 * Disable the potion.
	 */
	public void disablePotion() {
		dungeon.getPlayer().disablePotion();
	}

	/*
	 * Start the timer.
	 */
	public void usePotion() {
		potionTimer.play();
	}

	/*
	 * Check if the game is pause.
	 */
	public BooleanProperty isPause() {
		return this.pause;
	}

	/*
	 * Recursively create the timer of the potion.
	 */
	public Timeline createTimer(int cycle) {
		Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			if (pause.getValue() == false) {
				this.decrementCountdownTime();
			} else {
				potionTimer.stop();
				Thread t = new Thread(this);
				t.start();
			}
		}));
		timer.setCycleCount(cycle);
		return timer;
	}

	/*
	 * Run until the game resume in another thread.
	 */
	@Override
	public void run() {
		while (pause.getValue() == true) {
			continue;
		}
		Timeline newTimer = createTimer(this.getTime().getValue());
		newTimer.play();
		return;
	}

	/*
	 * Receives the update from the subject.
	 */
	@Override
	public void update(Subject obj) {
		System.out.println("Player standing on a potion");

	}

	/*
	 * Get the class name of the potion.
	 */
	@Override
	public String getClassName() {
		return "Potion";
	}

}
