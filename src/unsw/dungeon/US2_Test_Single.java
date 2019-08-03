package unsw.dungeon;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;

public class US2_Test_Single {

	@Test
	void testEnemySingleGoal() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Hound hound = new Hound(dungeon, 4, 5);
		dungeon.addEntity(hound);
		Mage mage = new Mage(dungeon, 2, 6);
		dungeon.addEntity(mage);
		JSONObject goal = new JSONObject("{\"goal\": \"enemies\" }");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		dungeon.killEnemy(hound);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		dungeon.killEnemy(mage);
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
	}

	@Test
	void testTriggerSingleGoal() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Switch switch1 = new Switch(dungeon, 2, 7);
		dungeon.addEntity(switch1);
		Switch switch2 = new Switch(dungeon, 3, 6);
		dungeon.addEntity(switch2);
		Switch switch3 = new Switch(dungeon, 4, 5);
		dungeon.addEntity(switch3);
		Boulder boulder1 = new Boulder(dungeon, 2, 6);
		dungeon.addEntity(boulder1);
		Boulder boulder2 = new Boulder(dungeon, 3, 5);
		dungeon.addEntity(boulder2);
		Boulder boulder3 = new Boulder(dungeon, 5, 5);
		dungeon.addEntity(boulder3);
		JSONObject goal = new JSONObject("{\"goal\": \"boulders\" }");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		boulder1.moveDown();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		boulder2.moveDown();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		boulder3.moveLeft();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
	}

	@Test
	void testExitSingleGoal() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Exit exit = new Exit(2, 4);
		dungeon.addEntity(exit);
		JSONObject goal = new JSONObject("{\"goal\": \"exit\" }");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		player.moveLeft();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
	}

	@Test
	void testTreasureSingleGoal() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Treasure treasure1 = new Treasure(3, 7);
		dungeon.addEntity(treasure1);
		Treasure treasure2 = new Treasure(3, 6);
		dungeon.addEntity(treasure2);
		Treasure treasure3 = new Treasure(3, 5);
		dungeon.addEntity(treasure3);
		JSONObject goal = new JSONObject("{\"goal\": \"treasure\" }");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(),"Goal checker error");
		player.moveDown();
		player.equipItem();
		assertFalse(dungeon.hasWin().getValue(),"Goal checker error");
		player.moveDown();
		player.equipItem();
		assertFalse(dungeon.hasWin().getValue(),"Goal checker error");
		player.moveDown();
		player.equipItem();
		assertTrue(dungeon.hasWin().getValue(),"Goal checker error");
		
	}

}
