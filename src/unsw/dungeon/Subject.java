package unsw.dungeon;

/**
 * Interface for all the subjects.
 *
 * A basic prototype to serve as the representation of a subject.
 *
 * @author Stephen Fong
 *
 */
public interface Subject {

	/*
	 * Attach an observer to the subject.
	 */
	public void attach(Observer o);

	/*
	 * Detach an observer from the subject.
	 */
	public void detach(Observer o);

	/*
	 * Notify all the observers of the subject.
	 */
	public void notifyObservers();

}
