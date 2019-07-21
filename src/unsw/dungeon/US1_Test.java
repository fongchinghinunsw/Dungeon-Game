package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * 1. When a player moves onto a grid with a key and press the space bar, the
 * key is added to the player's backpack and the key vanishes from the map. 2.
 * The player can only carry one key at a time.
 * 
 * @author z5211173
 *
 */
public class US1_Test {

	@Test
	public void testMoveTowardsWall() {
		// Test player can't move towards any locations when he's blocked by wall in all
		// directions.
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 5, 5);
		Wall wall1 = new Wall(4, 5);
		Wall wall2 = new Wall(5, 4);
		Wall wall3 = new Wall(5, 6);
		Wall wall4 = new Wall(6, 5);
		dungeon.addEntity(player);
		dungeon.addEntity(wall1);
		dungeon.addEntity(wall2);
		dungeon.addEntity(wall3);
		dungeon.addEntity(wall4);
		player.moveUp();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 5);
		player.moveDown();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 5);
		player.moveLeft();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 5);
		player.moveRight();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 5);
	}

	@Test
	public void testMoveTowardsLocationWithNoWall() {
		// Test player can move towards any locations if there're no walls blocking his
		// way.
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 5, 5);
		dungeon.addEntity(player);
		player.moveUp();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 4);
		player.moveDown();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 5);
		player.moveLeft();
		assertEquals(player.getX(), 4);
		assertEquals(player.getY(), 5);
		player.moveRight();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 5);
	}

	@Test
	public void testVanishFromMap() {
		Dungeon dungeon = new Dungeon(10, 10);
		Key key1 = new Key(dungeon, 5, 5);
		assertNotNull(key1, "Key not created");
		assertEquals(dungeon.numKey(), 0, "Initial numKey not zero!");
		dungeon.addKey();
		assertEquals(dungeon.numKey(), 1, "Key isn't added!");
		dungeon.addEntity(key1);
		Player player = new Player(dungeon, 5, 5);
		player.equipItem();
		Key keyInBag = player.findKey();
		assertNotNull(keyInBag, "Key not in bag!");
		assertEquals(keyInBag.getId(), key1.getId(), "ID of key changed!");
	}

	@Test
	public void testOneKey() {
		Dungeon dungeon = new Dungeon(10, 10);
		Key key1 = new Key(dungeon, 5, 5);
		assertNotNull(key1, "Key1 not created");
		assertEquals(dungeon.numKey(), 0, "Initial numKey not zero!");
		dungeon.addKey();
		assertEquals(dungeon.numKey(), 1, "Key1 isn't added!");
		dungeon.addEntity(key1);
		Key key2 = new Key(dungeon, 5, 6);
		assertNotNull(key2, "Key2 not created");
		assertEquals(dungeon.numKey(), 1, "numKey did not increase!");
		dungeon.addKey();
		assertEquals(dungeon.numKey(), 2, "Key2 isn't added!");
		dungeon.addEntity(key2);
		Player player = new Player(dungeon, 5, 5);
		assertTrue(player.equipItem(), "Player cannot pick up the key");
		player.setY(6);
		assertFalse(player.equipItem(), "Player picked up a second key");
		player.setY(5);
		assertFalse(player.equipItem(), "Forget about the key! It's already in your bag!");
	}
}
