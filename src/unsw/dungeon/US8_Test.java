package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

public class US8_Test {

	@Test
	public void testSetUpBomb() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Bomb bomb = new Bomb(dungeon, 3, 3);
		dungeon.addEntity(bomb);
		player.equipItem();
		player.setX(7);
		player.setY(4);
		Bomb bombFromBag = player.getBomb();
		assertNotNull(bombFromBag, "Picked up bomb is null");
		bomb.setX(player.getX());
		bomb.setY(player.getY());
		assertEquals(bomb.getX(), 7, "bomb not placed on right x value");
		assertEquals(bomb.getY(), 4, "bomb not placed on right y value");
		dungeon.addEntity(bomb);
		bomb.light();
		assertTrue(bomb.lit(), "Bomb not lit");
		player.getBag().removeItem("Bomb");
		assertNull(player.getBomb(), "Bomb not removed");
		bomb.explode();
		assertTrue(bomb.exploded(), "Bomb didn't explode");
	}

	@Test
	public void testBombBlowUp() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Bomb bomb = new Bomb(dungeon, 3, 3);
		dungeon.setPlayer(player);
		dungeon.addEntity(bomb);
		player.equipItem();
		player.setX(7);
		player.setY(4);
		Enemy enemy = new Hound(dungeon, 7, 3);
		dungeon.addEntity(player);
		dungeon.addEntity(enemy);
		Bomb bombFromBag = player.getBomb();
		assertNotNull(bombFromBag, "Picked up bomb is null");
		bomb.setX(player.getX());
		bomb.setY(player.getY());
		assertEquals(bomb.getX(), 7, "bomb not placed on right x value");
		assertEquals(bomb.getY(), 4, "bomb not placed on right y value");
		assertTrue(bomb.samePlace(player.getX(), player.getY()), "Player not in same place as bomb");
		assertTrue(bomb.adjacent(enemy.getX(), enemy.getY()) || bomb.samePlace(enemy.getX(), enemy.getY()),
				"Enemy not adjacent to bomb");
		dungeon.addObservers();
		dungeon.addEntity(bomb);
		bomb.light();
		dungeon.removeEquippedEntityFromBackPack(7, 4, "Bomb");
		assertTrue(bomb.lit(), "Bomb not lit");
		assertTrue(dungeon.hasBomb(7, 4), "Bomb not in dungeon");
		player.getBag().removeItem("Bomb");
		bomb.notifyObservers();
		bomb.explode();
		assertFalse(enemy.isAlive().getValue(), "Enemy not killed by bomb");
		assertFalse(player.isAlive().getValue(), "Player not killed by bomb");
		assertTrue(bomb.exploded(), "Bomb didn't explode");
	}

	@Test
	public void testOneBomb() {
		final JFXPanel fxPanel = new JFXPanel();
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Bomb bomb = new Bomb(dungeon, 3, 3);
		dungeon.setPlayer(player);
		dungeon.addEntity(bomb);
		Bomb bomb1 = new Bomb(dungeon, 7, 4);
		dungeon.addEntity(bomb1);
		dungeon.addEntity(player);
		player.equipItem();
		player.setX(7);
		player.setY(4);
		player.equipItem();
		player.setX(8);
		bomb.setX(player.getX());
		bomb.setY(player.getY());
		dungeon.addObservers();
		dungeon.addEntity(bomb);
		bomb.light();
		dungeon.removeEquippedEntityFromBackPack(7, 4, "Bomb");
		player.getBag().removeItem("Bomb");
		bomb.notifyObservers();
		assertTrue(dungeon.hasBomb(7, 4), "Allowing second bomb in same place");
	}

}
