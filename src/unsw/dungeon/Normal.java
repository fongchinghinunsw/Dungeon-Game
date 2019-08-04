package unsw.dungeon;

/*
 * Normal speed of the movable.
 */
public class Normal implements MoveSpeed {
	private long speedFactor = 50;

	/*
	 * Get speed factor.
	 */
	@Override
	public long getSpeedFactor() {
		return this.speedFactor;
	}

}
