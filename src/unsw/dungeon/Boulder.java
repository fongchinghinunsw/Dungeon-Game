package unsw.dungeon;

// Extends Entity for now, might change inheritance later.
public class Boulder extends Entity{

	public Boulder(int x, int y) {
		super(x, y);
	}

	@Override
	public String getClassName() {
		return "Boulder";
	}

}
