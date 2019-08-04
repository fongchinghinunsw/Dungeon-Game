package unsw.dungeon;

/**
 * Slow speed of a movable
 *
 * @author Stephen Fong
 *
 */
public class Slow implements MoveSpeed {

	private long speedFactor = 100;

	/*
	 * Return the speed factor.
	 */
	@Override
	public long getSpeedFactor() {
		return this.speedFactor;
	}

}
