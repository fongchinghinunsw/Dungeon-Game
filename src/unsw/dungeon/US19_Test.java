package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

public class US19_Test {

	@Test
	public void testClassMethods() {
		Dungeon dungeon = new Dungeon(10, 10);
		Potion potion = new Potion(dungeon, 2, 8);
		assertNotNull(potion, "Potion is null");
		assertEquals(potion.getX(), 2, "X of potion isn't set correctly");
		assertEquals(potion.getY(), 8, "Y of potion isn't set correctly");
		assertEquals(potion.getClassName(), "Potion", "getClassName incorrect");
		assertEquals(potion.getTime().getValue(), 5, "Countdown time incorrect");
		Player player = new Player(dungeon, 2, 8);
		assertFalse(player.isInvincible(), "Player born invincible");
	}

	@Test
	public void testEquipPotion() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 2, 8);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Potion potion = new Potion(dungeon, 2, 8);
		dungeon.addEntity(potion);
		assertTrue(player.equipItem(), "Cannot pickup potion");
		assertTrue(player.useItem("Potion"), "Cannot use potion");
		assertTrue(player.isInvincible(), "Player didn't become invincible");
		player.disablePotion();
		assertFalse(player.isInvincible(), "Effect of potion cannot be disabled");
	}

	@Test
	public void testOnePotionAtATime() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 2, 8);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Potion potion1 = new Potion(dungeon, 2, 8);
		dungeon.addEntity(potion1);
		Potion potion2 = new Potion(dungeon, 3, 8);
		dungeon.addEntity(potion2);
		assertTrue(player.equipItem(), "Cannot pickup potion 1");
		player.setX(3);
		assertTrue(player.equipItem(), "Cannot pickup potion 2");
		player.useItem("Potion");
		assertFalse(player.useItem("Potion"), "Second potion is used");
		player.disablePotion();
		assertTrue(player.useItem("Potion"), "Second potion cannot be used after first one wearing out");
	}

	@Test
	public void testKillEnemy() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 2, 8);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Potion potion1 = new Potion(dungeon, 2, 8);
		dungeon.addEntity(potion1);
		player.equipItem();
		player.useItem("Potion");
		Enemy enemy = new Hound(dungeon, 2, 8);
		dungeon.addEntity(enemy);
		enemy.update(player);
		assertTrue(player.isAlive().getValue(), "Player got killed");
		assertFalse(enemy.isAlive().getValue(), "Enemy still alive");
		player.disablePotion();
		Enemy enemy2 = new Hound(dungeon, 2, 8);
		dungeon.addEntity(enemy2);
		enemy2.update(player);
		assertFalse(player.isAlive().getValue(), "player still alive after potion wore off");
		assertTrue(enemy2.isAlive().getValue(), "Enemy killed by player with no potion");
	}

	@Test
	public void testRunAway() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 2, 8);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Potion potion1 = new Potion(dungeon, 2, 8);
		dungeon.addEntity(potion1);
		player.equipItem();
		player.useItem("Potion");
		Enemy enemy = new Hound(dungeon, 3, 8);
		dungeon.addEntity(enemy);
		enemy.findPlayer();
		assertTrue(enemy.getX() > 3, "enemy isn't running away!");
	}
}
