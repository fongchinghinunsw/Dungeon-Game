package unsw.dungeon;

import java.util.ArrayList;

public class Bomb extends Equipable implements Subject, Observer {

	private int countdownTime;
	private boolean exploded;
	private Dungeon dungeon;
	private boolean lit;
	private ArrayList<Observer> observers;

	public Bomb(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		countdownTime = 3;
		exploded = false;
		lit = false;
		observers = new ArrayList<>();
	}

	public int getTime() {
		return countdownTime;
	}

	public boolean exploded() {
		return exploded;
	}

	public void light() {
		lit = true;
	}

	public boolean lit() {
		return lit;
	}

	public void explode() {
		exploded = true;
	}

	@Override
	public String getClassName() {
		return "Bomb";
	}

	@Override
	public void update(Subject obj) {
		if (exploded) {
			return;
		}
	}

	@Override
	public void attach(Observer o) {
		if (!(observers.contains(o))) {
			observers.add(o);
		}
	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			Entity entity = (Entity) o;
			if (this.adjacent(entity.getX(), entity.getY()) || this.samePlace(entity.getX(), entity.getY())) {
				String className = ((Entity) o).getClassName();
				if (className.equals("Player") || className.equals("Boulder") || className.equals("Enemy")) {
					o.update(this);
					String message = "Bomb here. Just told the " + className + " that Imma blow'em up";
					System.out.println(message);
				}
			}
		}
	}

}
