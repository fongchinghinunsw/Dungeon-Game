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

	@Test
	void testPickUpSword() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Sword sword = new Sword(3, 4);
		dungeon.addEntity(sword);
		assertTrue(player.equipItem(), "Cannot pick up sword");
		assertNotNull(player.getBag().removeItem("Sword"), "Sword not in bag");
	}

	@Test
	void testOneAtATime() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Sword sword = new Sword(3, 4);
		dungeon.addEntity(sword);
		player.equipItem();
		Sword sword2 = new Sword(2, 4);
		dungeon.addEntity(sword);
		assertEquals(player.countSwordInBackPack(), 1, "Count of sword wrong");
		player.setX(2);
		assertFalse(player.equipItem(), "Picking up a second sword");
		assertEquals(player.countSwordInBackPack(), 1, "Count of sword wrong");
	}

}
