package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;

public class US20_Test {
	@Test
	public void testClassMethods() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Treasure treasure = new Treasure(3, 4);
		dungeon.addEntity(treasure);
		assertNotNull(treasure, "Treasure is null");
		assertEquals(treasure.getX(), 3, "X of treasure isn't set correctly");
		assertEquals(treasure.getY(), 4, "Y of treasure isn't set correctly");
		assertEquals(treasure.getClassName(), "Treasure", "getClassName set wrongly");
	}

	@Test
	public void testPickUpTreasure() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		Treasure treasure = new Treasure(3, 4);
		dungeon.addEntity(treasure);
		assertEquals(dungeon.countRemainingTreasure(), 1, "Count of remaining treasure wrong");
		player.equipItem();
		assertNotNull(player.getBag().removeItem("Treasure"), "Treasure not in bag");
		dungeon.removeEquippedEntityFromBackPack(3, 4, "Treasure");
		assertEquals(dungeon.countRemainingTreasure(), 0, "Count not updated properly");
		assertTrue(dungeon.completedTreasureGoal(), "Goal evaluation incorrect");

	}
}
