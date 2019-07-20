package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 1. When a player moves onto a grid with a key and press the space bar,
 * the key is added to the player's backpack and the key vanishes from the map.
 * 2. The player can only carry one key at a time.
 * 
 * @author z5211173
 *
 */
public class US6_Test {
	@Test
	public void testEquipKey() {
		Dungeon dungeon = new Dungeon(10, 10);
		Key key = new Key(dungeon, 5, 5);
		dungeon.addKey();
		dungeon.addEntity(key);
		Player player1 = new Player(dungeon, 5, 5);
		assertTrue(player1.equipItem(), "Can't put key in bag!");
		assertNotNull(player1.findKey(),"Key isn't added to bag!");
	}
	
	@Test
	public void testVanishFromMap() {
		Dungeon dungeon = new Dungeon(10, 10);
		Key key1 = new Key(dungeon, 5, 5);
		assertNotNull(key1,"Key not created");
		assertEquals(dungeon.numKey(),0,"Initial numKey not zero!");
		dungeon.addKey();
		assertEquals(dungeon.numKey(),1,"Key isn't added!");
		dungeon.addEntity(key1);
		Player player = new Player(dungeon, 5, 5);
		player.equipItem();
		Key keyInBag = player.findKey();
		assertNotNull(keyInBag,"Key not in bag!");
		assertEquals(keyInBag.getId(),key1.getId(),"ID of key changed!");
	}
	
	@Test
	public void testOneKey() {
		Dungeon dungeon = new Dungeon(10, 10);
		Key key1 = new Key(dungeon, 5, 5);
		assertNotNull(key1,"Key1 not created");
		assertEquals(dungeon.numKey(),0,"Initial numKey not zero!");
		dungeon.addKey();
		assertEquals(dungeon.numKey(),1,"Key1 isn't added!");
		dungeon.addEntity(key1);
		Key key2 = new Key(dungeon, 5, 6);
		assertNotNull(key2,"Key2 not created");
		assertEquals(dungeon.numKey(),1,"numKey did not increase!");
		dungeon.addKey();
		assertEquals(dungeon.numKey(),2,"Key2 isn't added!");
		dungeon.addEntity(key2);
		Player player = new Player(dungeon, 5, 5);
		assertTrue(player.equipItem(),"Player cannot pick up the key");
		player.setY(6);
		assertFalse(player.equipItem(),"Player picked up a second key");	
		player.setY(5);
		assertFalse(player.equipItem(),"Forget about the key! It's already in your bag!");
	}
}
