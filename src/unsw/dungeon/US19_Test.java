package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class US19_Test {

	@Test
	public void testClassMethods() {
		Dungeon dungeon = new Dungeon(10, 10);
		Potion potion = new Potion(2, 8);
		assertNotNull(potion, "Potion is null");
		assertEquals(potion.getX(), 2, "X of potion isn't set correctly");
		assertEquals(potion.getY(), 8, "Y of potion isn't set correctly");
		assertEquals(potion.getClassName(), "Potion", "getClassName incorrect");
		assertEquals(potion.getTime(), 5, "Countdown time incorrect");
		Player player = new Player(dungeon, 2, 8);
		assertFalse(player.isInvincible(), "Player born invincible");
	}

	@Test
	public void testEquipPotion() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 2, 8);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Potion potion = new Potion(2, 8);
		dungeon.addEntity(potion);
		assertTrue(player.equipItem(), "Cannot pickup potion");
		assertTrue(player.useItem("Potion"), "Cannot use potion");
		assertTrue(player.isInvincible(), "Player didn't become invincible");
		player.disablePotion();
		assertFalse(player.isInvincible(), "Effect of potion cannot be disabled");
	}

}
