package unsw.dungeon;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;

public class US11_Test {

	@Test
	void testKillEnemy_PlayerToEnemy() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Sword sword = new Sword(3, 4);
		dungeon.addEntity(sword);
		player.equipItem();
		Enemy enemy = new Enemy(dungeon, 2, 4);
		player.setX(2);
		player.update(enemy);
		assertTrue("Player died", player.isAlive().getValue());
		assertFalse(enemy.isAlive().getValue(), "Enemy didn't die");
		assertEquals(sword.getDurability(), 4, "Durability didn't decrease");
	}

	@Test
	void testKillEnemy_EnemyToPlayer() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Sword sword = new Sword(3, 4);
		dungeon.addEntity(sword);
		player.equipItem();
		Enemy enemy = new Enemy(dungeon, 2, 4);
		enemy.findPlayer();
		player.update(enemy);
		assertTrue(player.isAlive().getValue(), "Player died");
		assertFalse(enemy.isAlive().getValue(), "Enemy didn't die");
		assertEquals(sword.getDurability(), 4, "Durability didn't decrease");
	}
}
