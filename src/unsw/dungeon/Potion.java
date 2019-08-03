package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class Potion extends Equipable implements Observer, Runnable {

	Dungeon dungeon;
	private IntegerProperty countdownTime;
	private BooleanProperty pause;
	private Timeline potionTimer;

	public Potion(Dungeon dungeon, int x, int y) {
		super(x, y);
		countdownTime = new SimpleIntegerProperty(5);
		pause = new SimpleBooleanProperty();
		this.potionTimer = createTimer(countdownTime.getValue());
		this.dungeon = dungeon;
	}

	public IntegerProperty getTime() {
		return this.countdownTime;
	}

	public void decrementCountdownTime() {
		countdownTime.setValue(countdownTime.getValue() - 1);
	}

	public void disablePotion() {
		dungeon.getPlayer().disablePotion();
	}

	public void usePotion() {
		potionTimer.play();
	}

	public BooleanProperty isPause() {
		return this.pause;
	}

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

	@Override
	public String getClassName() {
		return "Potion";
	}

	@Override
	public void update(Subject obj) {
		System.out.println("Player standing on a potion");

	}

	@Override
	public void run() {
		while (pause.getValue() == true) {
			continue;
		}
		Timeline newTimer = createTimer(this.getTime().getValue());
		newTimer.play();
		return;
	}

}
