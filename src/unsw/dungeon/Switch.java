package unsw.dungeon;

public class Switch extends Entity implements Subject, Observer {
	public Switch(int x, int y) {
		super(x, y);
	}

	@Override
	public String getClassName() {
		return "Switch";
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
