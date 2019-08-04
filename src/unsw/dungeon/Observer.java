package unsw.dungeon;

/*
 * Representation of the Observer
 */
public interface Observer {

	/*
	 * Receive update from the subject.
	 */
	public void update(Subject obj);
}
