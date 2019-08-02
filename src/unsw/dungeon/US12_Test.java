package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;

public class US12_Test {

	@Test
	public void testDropSword() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Sword sword = new Sword(3, 4);
		dungeon.addEntity(sword);
		player.equipItem();
		dungeon.removeEquippedEntityFromBackPack(3, 4, "Sword");
		assertNull(player.removeSwordInBackPack(), "Sword not removed");
		assertEquals(player.countSwordInBackPack(), 0, "Count wasn't updated");
	}

	@Test
	public void testDropAndPickUp_Different() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Sword sword = new Sword(3, 4);
		dungeon.addEntity(sword);
		Sword sword2 = new Sword(4, 4);
		dungeon.addEntity(sword2);
		player.equipItem();
		dungeon.removeEquippedEntityFromBackPack(3, 4, "Sword");
		player.setX(4);
		assertTrue(player.equipItem(), "Can't pick up sword after dropping");
	}

	@Test
	public void testDropAndPickUp_Same() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Sword sword = new Sword(3, 4);
		dungeon.addEntity(sword);
		player.equipItem();
		player.setX(4);
		dungeon.removeEquippedEntityFromBackPack(4, 4, "Sword");
		player.moveDown();
		player.moveLeft();
		player.moveUp();
		player.moveRight();
		assertTrue(player.equipItem(), "Can't pick up sword after dropping");
	}

	@Test
	public void testDropAndMeetEnemy() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		Enemy enemy = new Hound(dungeon, 4, 4);
		dungeon.addEntity(enemy);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Sword sword = new Sword(3, 4);
		dungeon.addEntity(sword);
		player.equipItem();
		player.setX(4);
		dungeon.removeEquippedEntityFromBackPack(4, 4, "Sword");
		player.update(enemy);
		enemy.update(player);
		assertFalse(player.isAlive().getValue(), "Stayed alive without sword");
		assertTrue(enemy.isAlive().getValue(), "Died to player without sword or potion");
	}
}
