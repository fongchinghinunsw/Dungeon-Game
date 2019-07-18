package unsw.dungeon;

public class Equipable extends Entity{
	private String name;
	public Equipable(int x, int y, String name) {
		super(x, y);
		this.name = name;
	}
	
	public boolean equip(Player player) {
		
		return false;
	}
	
}
