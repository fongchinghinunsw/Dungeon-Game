package unsw.dungeon;

/**
 * Treasure entity of the dungeon.
 *
 * A basic prototype to serve as the representation of a treasure.
 *
 * @author Stephen Fong
 *
 */
public class Treasure extends Equipable implements Observer {
	/*
	 * Constructor of a treasure.
	 */
	public Treasure(int x, int y) {
		super(x, y);
	}

	/*
	 * Receive update from the subject.
	 */
	@Override
	public void update(Subject obj) {

	}

	/*
	 * Get the class name of the treasure.
	 */
	@Override
	public String getClassName() {
		return "Treasure";
	}
}
