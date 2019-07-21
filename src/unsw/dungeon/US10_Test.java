package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;

public class US10_Test {
	@Test
	public void testClassMethods() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Sword sword = new Sword(3, 4);
		dungeon.addEntity(sword);
		assertEquals(sword.getClassName(), "Sword", "getClassName set wrongly");
		assertNotNull(sword, "Sword is null");
		assertEquals(sword.getX(), 3, "X of sword isn't set correctly");
		assertEquals(sword.getY(), 4, "Y of sword isn't set correctly");
		assertEquals(sword.getDurability(), 5, "Durability initially wrongly set");
		sword.reduceDurability();
		assertEquals(sword.getDurability(), 4, "Durability cannot be decreased");
		for (int i = 0; i < 4; i++) {
			sword.reduceDurability();
		}
		assertEquals(sword.getDurability(), 0, "Durability cannot be decreased");
		sword.reduceDurability();
		assertEquals(sword.getDurability(), 0, "Durability still changes after turnin 0");

	}
}
