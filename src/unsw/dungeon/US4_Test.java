package unsw.dungeon;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

public class US4_Test {

	@Test
	public void testClassMethods() {
		Dungeon dungeon = new Dungeon(10, 10);
		Switch trigger = new Switch(dungeon, 0, 2);
		dungeon.addSwitch(trigger);
		dungeon.addEntity(trigger);
		assertEquals(trigger.getClassName(), "Switch", "getClassName wrong");
		assertTrue(trigger.samePlace(0, 2), "Location variables incorrectly intialized");
		assertNotNull(trigger, "Switch is initialized to be null");
	}

	@Test
	public void testTriggerSwitch() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Switch trigger = new Switch(dungeon, 0, 2);
		dungeon.addSwitch(trigger);
		dungeon.addEntity(trigger);
		Boulder boulder = new Boulder(dungeon, 0, 1);
		dungeon.addEntity(boulder);
		dungeon.addObserver(boulder);

		dungeon.notifyPlayerObservers();

		assertEquals(dungeon.countUntriggeredSwitch(), 1);

		player.moveDown();
		dungeon.notifyPlayerObservers();
		assertEquals(dungeon.countUntriggeredSwitch(), 0);
		player.moveDown();
		dungeon.notifyPlayerObservers();
		assertEquals(dungeon.countUntriggeredSwitch(), 1);

	}

}
