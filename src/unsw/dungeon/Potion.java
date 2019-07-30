package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class Potion extends Equipable implements Observer {

	Dungeon dungeon;
	private IntegerProperty countdownTime;
	private Timeline potionTimer;

	public Potion(Dungeon dungeon, int x, int y) {
		super(x, y);
		countdownTime = new SimpleIntegerProperty(5);
		this.potionTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> this.decrementCountdownTime()));
		potionTimer.setCycleCount(5);
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

	@Override
	public String getClassName() {
		return "Potion";
	}

	@Override
	public void update(Subject obj) {
		System.out.println("Player standing on a potion");

	}

}
