package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author z5211173
 *
 */
public class US7_Test {

	@Test
	public void testClassMethod() {
		Dungeon dungeon = new Dungeon(10, 10);
		Door door = new Door(dungeon, 5, 5);
		ArrayList<Entity> entities = dungeon.getEntities(5, 5);
		assertTrue(entities.isEmpty(), "Entities ArrayList isn't initialized to be empty");
		assertEquals(dungeon.numDoor(), 0, "Initial numDoor is not 0");
		dungeon.addDoor();
		assertEquals(dungeon.numDoor(), 1, "numDoor isn't increased");
		assertTrue(door.getState() instanceof ClosedState, "Initial state wrong");
		assertEquals(door.getClassName(), "Door", "getClassName wrong");
		dungeon.addEntity(door);
		entities = dungeon.getEntities(5, 5);
		assertFalse(entities.isEmpty(), "Door isn't in entities");
	}

	@Test
	public void testOnesieOpen() {
		Dungeon dungeon = new Dungeon(10, 10);
		Door door = new Door(dungeon, 5, 5);
		assertFalse(door.isOpen(), "r/gatesopencomeonin");
		door.setState(door.getOpenState());
		assertTrue(door.isOpen(), "r/gatekeeping");
		door.setState(door.getClosedState());
		assertTrue(door.isOpen(), "Opened doors shouldn't be closed again");
	}

	@Test
	public void testWrongKey() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Key key1 = new Key(dungeon, 3, 4);
		dungeon.addKey();
		dungeon.addEntity(key1);
		Key key2 = new Key(dungeon, 3, 3);
		dungeon.addKey();
		dungeon.addEntity(key2);
		Door door1 = new Door(dungeon, 5, 5);
		dungeon.addDoor();
		dungeon.addEntity(door1);
		assertTrue(player.equipItem(), "Cannot equip the key");
		player.setX(5);
		player.setY(5);
		door1.update(player);
		assertFalse(door1.isOpen(), "Door opened to wrong key");
		assertNotNull(player.findKey(), "Key removed even though it's wrong");
	}

	@Test
	public void testCorrectKey() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Key key1 = new Key(dungeon, 3, 3);
		dungeon.addKey();
		dungeon.addEntity(key1);
		Key key2 = new Key(dungeon, 3, 4);
		dungeon.addKey();
		dungeon.addEntity(key2);
		Door door1 = new Door(dungeon, 5, 5);
		dungeon.addDoor();
		dungeon.addEntity(door1);
		assertTrue(player.equipItem(), "Cannot equip the key");
		player.setX(5);
		player.setY(5);
		door1.update(player);
		assertTrue(door1.isOpen(), "Fails to open the door");
		assertNull(player.findKey(), "Key not removed");
	}
	
	@Test
	public void testPastOpenDoor() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Key key1 = new Key(dungeon, 3, 3);
		dungeon.addKey();
		dungeon.addEntity(key1);
		Door door1 = new Door(dungeon, 5, 5);
		dungeon.addDoor();
		dungeon.addEntity(door1);
		player.equipItem();
		player.setX(4);
		player.setY(5);
		door1.update(player);
		player.moveRight();
		assertEquals(player.getX(),5,"Can't walk pass door with right key");
	}
	
	@Test
	public void testPastClosedDoorNoKey() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Door door1 = new Door(dungeon, 5, 5);
		dungeon.addDoor();
		dungeon.addEntity(door1);
		player.equipItem();
		player.setX(4);
		player.setY(5);
		door1.update(player);
		player.moveRight();
		assertEquals(player.getX(),4,"Walked pass door with no key");
	}
	
	@Test
	public void testPastClosedDoorWrongKey() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Key key1 = new Key(dungeon, 1, 3);
		dungeon.addKey();
		dungeon.addEntity(key1);
		Key key2 = new Key(dungeon, 3, 3);
		dungeon.addKey();
		dungeon.addEntity(key2);
		Door door1 = new Door(dungeon, 5, 5);
		dungeon.addDoor();
		dungeon.addEntity(door1);
		player.equipItem();
		player.setX(4);
		player.setY(5);
		door1.update(player);
		player.moveRight();
		assertEquals(player.getX(),4,"Walked past door with wrong key");
	}
	
}
