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
	public void testAddDoor() {
		Dungeon dungeon = new Dungeon(10, 10);
		Door door = new Door(dungeon, 5, 5);
		ArrayList<Entity> entities = dungeon.getEntities(5, 5);
		assertTrue(entities.isEmpty(),"Entities ArrayList isn't initialized to be empty");
		assertEquals(dungeon.numDoor(),0,"Initial numDoor is not 0");
		dungeon.addDoor();
		assertEquals(dungeon.numDoor(),1,"numDoor isn't increased");
		dungeon.addEntity(door);
		entities = dungeon.getEntities(5, 5);
		assertFalse(entities.isEmpty(),"Door isn't in entities");
	}
	
	@Test
	public void testOnesieOpen() {
		Dungeon dungeon = new Dungeon(10, 10);
		Door door = new Door(dungeon, 5, 5);
		assertFalse(door.isOpen(),"r/gatesopencomeonin");
		door.setState(door.getOpenState());
		assertTrue(door.isOpen(),"r/gatekeeping");
		door.setState(door.getClosedState());
		assertTrue(door.isOpen(),"Opened doors shouldn't be closed again");
	}
}
