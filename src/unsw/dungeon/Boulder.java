package unsw.dungeon;

// Extends Character class for now, open to modification later.
public class Boulder extends Entity implements Subject, Observer {

	public Boulder(int x, int y) {
		super(x, y);
	}

	@Override
	public String getClassName() {
		return "Boulder";
	}

	@Override
	public void update(Subject obj, Dungeon dungeon) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attach(Observer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void detach(Observer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub

	}

}
