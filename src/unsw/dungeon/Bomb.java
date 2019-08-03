package unsw.dungeon;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class Bomb extends Equipable implements Subject, Observer, Runnable {

	private Dungeon dungeon;
	private IntegerProperty countdownTime;
	private Timeline bombTimer;
	private boolean exploded;
	private boolean lit;
	private BooleanProperty pause;
	private ArrayList<Observer> observers;

	public Bomb(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		pause = new SimpleBooleanProperty(false);
		countdownTime = new SimpleIntegerProperty(4);
		exploded = false;
		lit = false;
		observers = new ArrayList<>();
		this.bombTimer = createTimer(countdownTime.getValue());
		bombTimer.setCycleCount(4);
	}

	public IntegerProperty getTime() {
		return countdownTime;
	}

	public void decrementCountdownTime() {
		countdownTime.setValue(countdownTime.getValue() - 1);
	}

	public boolean exploded() {
		return exploded;
	}

	public boolean lit() {
		return lit;
	}

	public void light() {
		bombTimer.play();
		lit = true;
	}

	public void explode() {
		exploded = true;
	}

	@Override
	public void update(Subject obj) {
		if (exploded) {
			return;
		}
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
		for (Observer o : observers) {
			Entity entity = (Entity) o;
			if (this.adjacent(entity.getX(), entity.getY()) || this.samePlace(entity.getX(), entity.getY())) {
				String className = ((Entity) o).getClassName();
				if (className.equals("Player") || className.equals("Boulder") || className.equals("Enemy")) {
					o.update(this);
					String message = "Bomb here. Just told the " + className + " that Imma blow'em up";
					System.out.println(message);
				}
			}
		}
	}

	public BooleanProperty isPause() {
		return this.pause;
	}

	public Timeline createTimer(int cycle) {
		Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			if (pause.getValue() == false) {
				this.decrementCountdownTime();
			} else {
				bombTimer.stop();
				Thread t = new Thread(this);
				t.start();
			}
		}));
		timer.setCycleCount(cycle);
		return timer;
	}

	@Override
	public String getClassName() {
		return "Bomb";
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
