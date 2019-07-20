package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 1. When a player moves onto a grid with a bomb and press the space bar, it
 * goes into the player's backpack and the bomb vanishes from the map. 
 * 2. The player can pick up as many bombs as he wants.
 * 
 * @author z5211173
 *
 */
public class US5_Test {

	@Test
	public void testObjectConstruction(){
		Dungeon dungeon = new Dungeon(10, 10);
		Bomb bomb = new Bomb(dungeon, 5, 6);
		assertEquals(bomb.getX(),5,"X of bomb isn't set correctly");
		assertEquals(bomb.getY(),6,"Y of bomb isn't set correctly");
		assertFalse(bomb.lit(),"lit isn't set correctly");
		assertFalse(bomb.exploded(),"Exploded isn't set correctly");
		assertEquals(bomb.getTime(),3,"Countdown time isn't set correctly");
		assertEquals(bomb.getClassName(),"Bomb","getClassName method is wrong");
	}
	
	@Test
	public void testEquipBomb() {
		Dungeon dungeon = new Dungeon(10, 10);
		Bomb bomb = new Bomb(dungeon, 5, 6);
		dungeon.addBomb(bomb);
		dungeon.addEntity(bomb);
		Bomb bomb1 = new Bomb(dungeon, 3, 6);
		dungeon.addBomb(bomb1);
		dungeon.addEntity(bomb1);
		Player player1 = new Player(dungeon, 5, 6);
		Player player2 = new Player(dungeon, 1, 6);
		assertTrue(player1.equipItem(),"Cannot equip bomb");
		assertFalse(player2.equipItem(),"Player2 shouldn't be able to equip bomb!");
		assertNotNull(player1.getBomb(),"Bomb isn't equipped successfully");
		assertNull(player2.getBomb(),"Player2 has bomb but he shouldn't");
		Backpack bag = player1.getBag();
		Bomb bagBomb = bag.getBomb();
		assertNotNull(bagBomb,"Player1's backpack cannot find the bomb");
		player2.setX(3);
		assertTrue(player2.equipItem(),"Player2 cannot pick up bomb after walking onto it");
	}

	@Test
	public void testMultipleBombs() {
		Dungeon dungeon = new Dungeon(10, 10);
		Bomb bomb1 = new Bomb(dungeon, 5, 5);
		dungeon.addBomb(bomb1);
		dungeon.addEntity(bomb1);
		Bomb bomb2 = new Bomb(dungeon, 5, 6);
		dungeon.addBomb(bomb2);
		dungeon.addEntity(bomb2);
		Bomb bomb3 = new Bomb(dungeon, 5, 7);
		dungeon.addBomb(bomb3);
		dungeon.addEntity(bomb3);
		Player player1 = new Player(dungeon, 5, 5);
		assertTrue(player1.equipItem(), "Can't equip bomb1");
		player1.setY(6);
		assertTrue(player1.equipItem(), "Can't equip bomb2");
		player1.setY(7);
		assertTrue(player1.equipItem(), "Can't equip bomb3");

	}

	@Test
	public void testVanishFromMap() {
		Dungeon dungeon = new Dungeon(10, 10);
		Bomb bomb1 = new Bomb(dungeon, 5, 5);
		dungeon.addBomb(bomb1);
		dungeon.addEntity(bomb1);
		Player player = new Player(dungeon, 5, 5);
		player.equipItem();
		assertFalse(dungeon.hasBomb(5, 5), "Bomb is still there");
	}
}
