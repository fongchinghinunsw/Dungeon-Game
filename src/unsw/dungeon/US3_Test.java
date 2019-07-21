package unsw.dungeon;

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
		// Test player can move towards any locations if there're no walls blocking his
		// way.
		Dungeon dungeon = new Dungeon(10, 10);
		/*
		 * Enemy enemy = new Enemy(dungeon, 5, 5); Boulder boulder1 = new
		 * Boulder(dungeon, 5, 4);
		 * 
		 * dungeon.addEntity(enemy); dungeon.addEntity(boulder1);
		 * dungeon.addObserver(boulder1);
		 * 
		 * // enemy.moveUp(); assertEquals(enemy.getX(), 5); assertEquals(enemy.getY(),
		 * 5); assertEquals(boulder1.getX(), 5); assertEquals(boulder1.getY(), 4);
		 */

	}

}
