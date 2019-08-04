package unsw.dungeon;

/**
 * fast speed moves at 0.25s per grid
 * 
 * @author z5211173
 *
 */
public class Fast implements MoveSpeed {
	private long speedFactor = 25;

	@Override
	public long getSpeedFactor() {
		return this.speedFactor;
	}

}
