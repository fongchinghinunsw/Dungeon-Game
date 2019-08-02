package unsw.dungeon;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;
import unsw.dungeon.CompositeGoal.Operator;

public class US2_Test_Composite {

	@Test
	void testGenericAND() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Hound hound = new Hound(dungeon, 4, 5);
		dungeon.addEntity(hound);
		Mage mage = new Mage(dungeon, 2, 6);
		dungeon.addEntity(mage);
		Switch switch1 = new Switch(dungeon, 2, 7);
		dungeon.addEntity(switch1);
		Switch switch2 = new Switch(dungeon, 3, 6);
		dungeon.addEntity(switch2);
		Boulder boulder1 = new Boulder(dungeon, 2, 6);
		dungeon.addEntity(boulder1);
		Boulder boulder2 = new Boulder(dungeon, 3, 5);
		dungeon.addEntity(boulder2);
		SingleGoal goal1 = new SingleGoal("enemies", dungeon);
		SingleGoal goal2 = new SingleGoal("boulders", dungeon);
		CompositeGoal mainGoal = new CompositeGoal(Operator.AND);
		mainGoal.add(goal1);
		mainGoal.add(goal2);
		dungeon.setGoalExpression(mainGoal);
		assertFalse(dungeon.hasWin(), "Goal checker error");
		dungeon.killEnemy(hound);
		assertFalse(dungeon.hasWin(), "Goal checker error");
		dungeon.killEnemy(mage);
		assertFalse(dungeon.hasWin(), "Goal checker error");
		boulder1.moveDown();
		assertFalse(dungeon.hasWin(), "Goal checker error");
		boulder2.moveDown();
		assertTrue(dungeon.hasWin(), "Goal checker error");
	}

	@Test
	void testGenericOR() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Hound hound = new Hound(dungeon, 4, 5);
		dungeon.addEntity(hound);
		Mage mage = new Mage(dungeon, 2, 6);
		dungeon.addEntity(mage);
		Switch switch1 = new Switch(dungeon, 2, 7);
		dungeon.addEntity(switch1);
		Switch switch2 = new Switch(dungeon, 3, 6);
		dungeon.addEntity(switch2);
		Boulder boulder1 = new Boulder(dungeon, 2, 6);
		dungeon.addEntity(boulder1);
		Boulder boulder2 = new Boulder(dungeon, 3, 5);
		dungeon.addEntity(boulder2);
		SingleGoal goal1 = new SingleGoal("enemies", dungeon);
		SingleGoal goal2 = new SingleGoal("boulders", dungeon);
		CompositeGoal mainGoal = new CompositeGoal(Operator.OR);
		mainGoal.add(goal1);
		mainGoal.add(goal2);
		dungeon.setGoalExpression(mainGoal);
		assertFalse(dungeon.hasWin(), "Goal checker error");
		boulder1.moveDown();
		assertFalse(dungeon.hasWin(), "Goal checker error");
		boulder2.moveDown();
		assertTrue(dungeon.hasWin(), "Goal checker error");
		boulder2.moveUp();
		assertFalse(dungeon.hasWin(), "Goal checker error");
		dungeon.killEnemy(hound);
		assertFalse(dungeon.hasWin(), "Goal checker error");
		dungeon.killEnemy(mage);
		assertTrue(dungeon.hasWin(), "Goal checker error");
	}

	@Test
	void testExitLast() {
		// Exit goal needs to be completed last
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Treasure gold1 = new Treasure(3, 5);
		dungeon.addEntity(gold1);
		Treasure gold2 = new Treasure(2, 5);
		dungeon.addEntity(gold2);
		Treasure gold3 = new Treasure(2, 6);
		dungeon.addEntity(gold3);
		Exit exit = new Exit(3, 6);
		dungeon.addEntity(exit);
		SingleGoal goal1 = new SingleGoal("treasure", dungeon);
		SingleGoal goal2 = new SingleGoal("exit", dungeon);
		CompositeGoal mainGoal = new CompositeGoal(Operator.AND);
		mainGoal.add(goal1);
		mainGoal.add(goal2);
		dungeon.setGoalExpression(mainGoal);
		assertFalse(dungeon.hasWin(), "Goal checker error");
		// Go to exit first to 'complete exit goal
		player.moveDown();
		player.moveDown();
		assertFalse(dungeon.hasWin(), "Goal checker error");
		// complete treasure goal but shouldn't win after that
		player.moveUp();
		player.equipItem();
		player.moveLeft();
		player.equipItem();
		player.moveDown();
		player.equipItem();
		assertFalse(dungeon.hasWin(), "Goal checker error");
		// go to exit and finally win
		player.moveRight();
		assertTrue(dungeon.hasWin(), "Goal checker error");
	}

	// testing (a and (b or c))
	void testComposite1() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 8, 8);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Hound hound = new Hound(dungeon, 4, 5);
		dungeon.addEntity(hound);
		Mage mage = new Mage(dungeon, 2, 6);
		dungeon.addEntity(mage);
		Switch switch1 = new Switch(dungeon, 2, 7);
		dungeon.addEntity(switch1);
		Switch switch2 = new Switch(dungeon, 3, 6);
		dungeon.addEntity(switch2);
		Boulder boulder1 = new Boulder(dungeon, 2, 6);
		dungeon.addEntity(boulder1);
		Boulder boulder2 = new Boulder(dungeon, 3, 5);
		dungeon.addEntity(boulder2);
		Treasure gold1 = new Treasure(8, 7);
		dungeon.addEntity(gold1);
		Treasure gold2 = new Treasure(8, 6);
		dungeon.addEntity(gold2);
		SingleGoal goal1 = new SingleGoal("enemies", dungeon);
		SingleGoal goal2 = new SingleGoal("treasure", dungeon);
		CompositeGoal subGoal1 = new CompositeGoal(Operator.OR);
		subGoal1.add(goal1);
		subGoal1.add(goal2);
		SingleGoal subGoal2 = new SingleGoal("boulders", dungeon);
		// BOULDER && (ENEMY || TREASURE)
		CompositeGoal mainGoal = new CompositeGoal(Operator.AND);
		mainGoal.add(subGoal1);
		mainGoal.add(subGoal2);
		dungeon.setGoalExpression(mainGoal);
		assertFalse(dungeon.hasWin(), "Goal checker error");
		// Boulder only
		boulder1.moveDown();
		boulder2.moveDown();
		assertFalse(dungeon.hasWin(), "Goal checker error");
		boulder1.moveUp();
		// Treasure Only
		player.moveUp();
		player.equipItem();
		player.moveUp();
		player.equipItem();
		assertFalse(dungeon.hasWin(), "Goal checker error");
		// Boulder and treasure
		boulder1.moveDown();
		assertTrue(dungeon.hasWin(), "Goal checker error");
		// Boulder, treasure and enemy
		dungeon.killEnemy(hound);
		dungeon.killEnemy(mage);
		assertTrue(dungeon.hasWin(), "Goal checker error");
	}

	// testing (a or (b and c))
	void testComposite2() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 8, 8);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Hound hound = new Hound(dungeon, 4, 5);
		dungeon.addEntity(hound);
		Mage mage = new Mage(dungeon, 2, 6);
		dungeon.addEntity(mage);
		Switch switch1 = new Switch(dungeon, 2, 7);
		dungeon.addEntity(switch1);
		Switch switch2 = new Switch(dungeon, 3, 6);
		dungeon.addEntity(switch2);
		Boulder boulder1 = new Boulder(dungeon, 2, 6);
		dungeon.addEntity(boulder1);
		Boulder boulder2 = new Boulder(dungeon, 3, 5);
		dungeon.addEntity(boulder2);
		Treasure gold1 = new Treasure(8, 7);
		dungeon.addEntity(gold1);
		Treasure gold2 = new Treasure(8, 6);
		dungeon.addEntity(gold2);
		SingleGoal goal1 = new SingleGoal("enemies", dungeon);
		SingleGoal goal2 = new SingleGoal("treasure", dungeon);
		CompositeGoal subGoal1 = new CompositeGoal(Operator.AND);
		subGoal1.add(goal1);
		subGoal1.add(goal2);
		SingleGoal subGoal2 = new SingleGoal("boulders", dungeon);
		// BOULDER || (ENEMY && TREASURE)
		CompositeGoal mainGoal = new CompositeGoal(Operator.OR);
		mainGoal.add(subGoal1);
		mainGoal.add(subGoal2);
		dungeon.setGoalExpression(mainGoal);
		// Boulder only
		boulder1.moveDown();
		boulder2.moveDown();
		assertFalse(dungeon.hasWin(), "Goal checker error");
		boulder1.moveUp();
		// Enemy only
		dungeon.killEnemy(hound);
		dungeon.killEnemy(mage);
		assertFalse(dungeon.hasWin(), "Goal checker error");
		// Enemy and Treasure only
		player.moveUp();
		player.equipItem();
		player.moveUp();
		player.equipItem();
		assertFalse(dungeon.hasWin(), "Goal checker error");
		// enemy and treasure and boulder
		boulder1.moveDown();
		assertTrue(dungeon.hasWin(), "Goal checker error");
	}
}
