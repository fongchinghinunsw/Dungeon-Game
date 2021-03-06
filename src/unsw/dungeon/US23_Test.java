package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;

// Not correct anymore as pathfinding algorithm implemented
public class US23_Test {
	@Test
	public void testClassMethods() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 2, 8);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Hound(dungeon, 3, 3);
		assertEquals(enemy.getClassName(), "Enemy", "getClassName set wrongly");
		assertTrue(enemy.getSpeedFactor() == 1, "Speed set wrongly");
		assertTrue(enemy.isAlive().getValue(), "Enemy initialized to be dead");
		enemy.die();
		assertFalse(enemy.isAlive().getValue(), "Enemy can't die");
	}

	@Test
	public void testFindPlayer() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 2, 3);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Hound(dungeon, 3, 3);
		dungeon.addEntity(enemy);
		enemy.findPlayer();
		assertTrue(enemy.samePlace(2, 3), "Didn't chase player left");
		player.setX(3);
		enemy.findPlayer();
		assertTrue(enemy.samePlace(3, 3), "Didn't chase player right");
		player.setY(2);
		enemy.findPlayer();
		assertTrue(enemy.samePlace(3, 2), "Didn't chase player up");
		player.setY(3);
		enemy.findPlayer();
		assertTrue(enemy.samePlace(3, 3), "Didn't chase player down");
	}

	@Test
	public void testKillPlayer() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Hound(dungeon, 3, 3);
		dungeon.addEntity(enemy);
		enemy.update(player);
		assertTrue(enemy.isAlive().getValue(), "Enemy died");
		assertFalse(player.isAlive().getValue(), "Player didn't die");
	}
}
