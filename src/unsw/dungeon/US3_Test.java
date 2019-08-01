package unsw.dungeon;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

public class US3_Test {

	@Test
	public void testMoveTowardsBoulder() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		Boulder boulder1 = new Boulder(dungeon, 0, 1);
		Boulder boulder2 = new Boulder(dungeon, 0, 2);
		Boulder boulder3 = new Boulder(dungeon, 1, 0);
		dungeon.addObserver(boulder3);
		// notify the boulder the movement of the player so that it can react.
		dungeon.notifyPlayerObservers();

		dungeon.addEntity(player);
		dungeon.addEntity(boulder1);
		dungeon.addEntity(boulder2);
		dungeon.addEntity(boulder3);

		player.moveDown();
		dungeon.notifyPlayerObservers();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);

		player.moveRight();
		dungeon.notifyPlayerObservers();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(boulder3.getX(), 2);
		assertEquals(boulder3.getY(), 0);

	}

	@Test
	public void testBlockEnemyWay() {
		final JFXPanel fxPanel = new JFXPanel();
		// Test player can move towards any locations if there're no walls blocking his
		// way.
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 2, 2);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Hound(dungeon, 5, 5);
		Boulder boulder = new Boulder(dungeon, 5, 4);
		dungeon.addEntity(enemy);
		dungeon.addEntity(boulder);
		enemy.moveUp();
		assertEquals(enemy.getY(), 5, "Moves through the boulder");
		enemy.moveDown();
		assertEquals(enemy.getY(), 6, "Doesn't move even without obstacles");
	}

	@Test
	public void testPushToWall() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		Boulder boulder = new Boulder(dungeon, 0, 1);
		Wall wall = new Wall(0, 2);
		dungeon.addEntity(wall);
		dungeon.addObserver(boulder);
		// notify the boulder the movement of the player so that it can react.
		dungeon.notifyPlayerObservers();

		dungeon.addEntity(player);
		dungeon.addEntity(boulder);

		player.moveDown();
		dungeon.notifyPlayerObservers();
		assertTrue(player.samePlace(0, 0), "Player moved");
		assertTrue(boulder.samePlace(0, 1), "Boulder moved");
		assertTrue(wall.samePlace(0, 2), "WALL MOVED WE'VE GOT A BIG PROBLEM");
	}

	@Test
	public void testPushToEquipables() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Potion potion = new Potion(dungeon, 0, 2);
		dungeon.addEntity(potion);
		Sword sword = new Sword(0, 3);
		dungeon.addEntity(sword);
		Key key = new Key(dungeon, 0, 4);
		dungeon.addKey();
		dungeon.addEntity(key);
		Bomb bomb = new Bomb(dungeon, 0, 5);
		dungeon.addEntity(bomb);
		Boulder boulder = new Boulder(dungeon, 0, 1);
		dungeon.addEntity(boulder);
		dungeon.addObserver(boulder);

		dungeon.notifyPlayerObservers();
		for (int i = 0; i < 5; i++) {
			player.moveDown();
			dungeon.notifyPlayerObservers();
		}
		dungeon.notifyPlayerObservers();
		assertTrue(player.samePlace(0, 5), "Where's the player?");
		assertTrue(potion.samePlace(0, 2), "Where's the potion?");
		assertTrue(sword.samePlace(0, 3), "Where's the sword?");
		assertTrue(key.samePlace(0, 4), "Where's the key?");
		assertTrue(bomb.samePlace(0, 5), "Where's the bomb?");
		assertTrue(boulder.samePlace(0, 6), "Where's the boulder?");

		for (int i = 0; i < 4; i++) {
			assertTrue(player.equipItem(), "Cannot equip item here");
			player.moveUp();
		}
	}
}
