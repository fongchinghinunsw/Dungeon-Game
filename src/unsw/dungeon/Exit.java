package unsw.dungeon;

/**
 * Player can stand on exit and walk through the exit
 * 
 * @author z5211173
 *
 */
public class Exit extends Entity implements Observer {

	/**
	 * constructor for exit
	 * 
	 * @param x
	 * @param y
	 */
	public Exit(int x, int y) {
		super(x, y);
	}

	@Override
	public String getClassName() {
		return "Exit";
	}

	@Override
	public void update(Subject obj) {
		System.out.println("You are standing on the exit.");

	}

}
