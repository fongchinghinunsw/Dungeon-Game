package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;

public class US12_Test {

	@Test
	public void testClassMethods() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Treasure treasure = new Treasure(3, 4);
		dungeon.addTreasure(treasure);
		dungeon.addEntity(treasure);
		assertNotNull(treasure, "Treasure is null");
		assertEquals(treasure.getX(), 3, "X of treasure isn't set correctly");
		assertEquals(treasure.getY(), 4, "Y of treasure isn't set correctly");
		assertEquals(treasure.getClassName(), "Treasure", "getClassName set wrongly");
	}
	
}
