package unsw.dungeon;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 1. When a player moves onto a grid with a key and press the space bar, the
 * key is added to the player's backpack and the key vanishes from the map. 2.
 * The player can only carry one key at a time.
 * 
 * @author z5211173
 *
 */
public class US3_Test {

	@Test
	public void testMoveTowardsBoulder() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		Wall wall1 = new Wall(4, 5);
		Boulder boulder1 = new Boulder(dungeon, 0, 1);
		Boulder boulder2 = new Boulder(dungeon, 0, 2);
		Boulder boulder3 = new Boulder(dungeon, 1, 0);
		dungeon.addObserver(boulder3);
		dungeon.notifyPlayerObservers();

		dungeon.addEntity(player);
		dungeon.addEntity(wall1);
		dungeon.addEntity(boulder1);
		dungeon.addEntity(boulder2);
		dungeon.addEntity(boulder3);

		player.moveDown();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);

		player.moveRight();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(boulder3.getX(), 2);
		assertEquals(boulder3.getY(), 0);

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
	public void testMoveTowardsLocationWithOtherEntities() {
		// Test player can move towards any locations if there're no walls blocking his
		// way.
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 5, 5);

		dungeon.setPlayer(player);

		Sword sword = new Sword(5, 4);
		Potion potion = new Potion(5, 5);
		Bomb bomb = new Bomb(dungeon, 5, 6);
		Exit exit = new Exit(5, 6);
		Key key = new Key(dungeon, 5, 7);
		Door door = new Door(dungeon, 5, 8);
		Treasure treasure = new Treasure(5, 9);
		Boulder boulder = new Boulder(dungeon, 6, 9);
		Switch floorSwitch = new Switch(dungeon, 7, 9);

		dungeon.addEntity(player);
		dungeon.addEntity(sword);
		dungeon.addEntity(potion);
		dungeon.addEntity(bomb);
		dungeon.addEntity(exit);
		dungeon.addEntity(key);
		dungeon.addEntity(door);
		dungeon.addEntity(treasure);
		dungeon.addEntity(boulder);
		dungeon.addEntity(floorSwitch);

		player.moveUp();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 4);
		player.moveDown();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 5);
		player.moveDown();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 6);
		player.moveDown();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 7);
		player.moveDown();
		assertEquals(player.getX(), 5);
		assertNotEquals(player.getY(), 8);
		player.moveLeft();
		player.moveDown();
		player.moveDown();
		player.moveRight();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 9);
		player.moveRight();
		assertEquals(player.getX(), 6);
		assertEquals(player.getY(), 9);
		player.moveRight();
		assertEquals(player.getX(), 7);
		assertEquals(player.getY(), 9);
	}

}
