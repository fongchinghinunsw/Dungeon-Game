package unsw.dungeon;

// Extends Character class for now, open to modification later.
public class Boulder extends Movable implements Subject, Observer {

	public Boulder(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
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

	@Override
	public long getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

}
