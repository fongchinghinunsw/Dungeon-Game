package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class US8_Test {

	@Test
	public void testSetUpBomb() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 3);
		Bomb bomb = new Bomb(dungeon, 3, 3);
		dungeon.addBomb(bomb);
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
		bomb.explode();
		assertTrue(bomb.exploded(), "Bomb didn't explode");
	}

}
