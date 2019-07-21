package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class US14_Test {

	@Test
	public void testClassMethods() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Backpack backpack = player.getBag();
		assertNotNull(backpack, "Backpack is null");
		for (int i = 0; i < 9; i++) {
			Equipable item = new Equipable(2, 4);
			assertTrue(backpack.addItem(item), "Cannot fit in backpack");
		}
		Equipable item = new Equipable(2, 4);
		assertFalse(backpack.addItem(item), "Over capacity");
	}

	@Test
	public void testInAndOut() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Backpack backpack = player.getBag();
		assertNotNull(backpack, "Backpack is null");
		for (int i = 0; i < 9; i++) {
			Equipable item = new Equipable(2, 4);
			assertTrue(backpack.addItem(item), "Cannot fit in backpack");
			backpack.removeItem("Equipable");
		}
		for (int i = 0; i < 9; i++) {
			Equipable item = new Equipable(2, 4);
			assertTrue(backpack.addItem(item), "Cannot fit in backpack");
		}
		backpack.removeItem("Equipable");
		Equipable item = new Equipable(2, 4);
		assertTrue(backpack.addItem(item), "Cannot fit in backpack");
	}

	@Test
	public void testRemoveNonexistentItem() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Backpack backpack = player.getBag();
		assertNull(backpack.removeItem("Equipable"), "equipable out of nowhere");
		Equipable item = new Equipable(2, 4);
		assertTrue(backpack.addItem(item), "Cannot fit in backpack");
		assertNotNull(backpack.removeItem("Equipable"), "can't find equipable");
		assertNull(backpack.removeItem("Equipable"),"removed item out of nowhere");
	}
}
