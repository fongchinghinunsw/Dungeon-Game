package unsw.dungeon;

/**
 * Switch entity of the dungeon.
 *
 * A basic prototype to serve as the representation of a switch.
 *
 * @author Stephen Fong
 *
 */
public class Switch extends Entity implements Observer {

	private Dungeon dungeon;

	/*
	 * Constructor of the switch.
	 */
	public Switch(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	/*
	 * Receives update from the subject.
	 */
	@Override
	public void update(Subject obj) {

	}

	/*
	 * Get the class name of the switch.
	 */
	@Override
	public String getClassName() {
		return "Switch";
	}
}
