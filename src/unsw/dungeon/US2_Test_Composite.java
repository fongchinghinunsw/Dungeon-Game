package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

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
		JSONObject goal = new JSONObject("{ \"goal\": \"AND\", \"subgoals\":\n" + "  [ { \"goal\": \"enemies\" },\n"
				+ "    { \"goal\": \"boulders\"}\n" + "  ]\n" + "}");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		dungeon.killEnemy(hound);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		dungeon.killEnemy(mage);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		boulder1.moveDown();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		boulder2.moveDown();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
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
		JSONObject goal = new JSONObject("{ \"goal\": \"OR\", \"subgoals\":\n" + "  [ { \"goal\": \"enemies\" },\n"
				+ "    { \"goal\": \"boulders\"}\n" + "  ]\n" + "}");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		boulder1.moveDown();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		boulder2.moveDown();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
		boulder2.moveUp();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		dungeon.killEnemy(hound);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		dungeon.killEnemy(mage);
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
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
		JSONObject goal = new JSONObject("{ \"goal\": \"AND\", \"subgoals\":\n" + "  [ { \"goal\": \"exit\" },\n"
				+ "    { \"goal\": \"treasure\"}\n" + "  ]\n" + "}\n" + "");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// Go to exit first to 'complete exit goal
		player.moveDown();
		player.moveDown();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// complete treasure goal but shouldn't win after that
		player.moveUp();
		player.equipItem();
		player.moveLeft();
		player.equipItem();
		player.moveDown();
		player.equipItem();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// go to exit and finally win
		player.moveRight();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
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
		// BOULDER && (ENEMY || TREASURE)
		JSONObject goal = new JSONObject("{ \"goal\": \"AND\", \"subgoals\":\n" + "  [ { \"goal\": \"boulders\" },\n"
				+ "    { \"goal\": \"OR\", \"subgoals\":\n" + "  [ { \"goal\": \"enemies\"},\n"
				+ "  	{ \"goal\": \"treasure\"}\n" + "  ]\n" + "  }\n" + "}\n" + "");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// Boulder only
		boulder1.moveDown();
		boulder2.moveDown();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		boulder1.moveUp();
		// Treasure Only
		player.moveUp();
		player.equipItem();
		player.moveUp();
		player.equipItem();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// Boulder and treasure
		boulder1.moveDown();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
		// Boulder, treasure and enemy
		dungeon.killEnemy(hound);
		dungeon.killEnemy(mage);
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
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
		// BOULDER || (ENEMY && TREASURE)
		JSONObject goal = new JSONObject("{ \"goal\": \"AND\", \"subgoals\":\n" + "  [ { \"goal\": \"boulders\" },\n"
				+ "    { \"goal\": \"OR\", \"subgoals\":\n" + "  [ { \"goal\": \"enemies\"},\n"
				+ "  	{ \"goal\": \"treasure\"}]\n" + "  }]\n" + "}");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// Boulder only
		boulder1.moveDown();
		boulder2.moveDown();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		boulder1.moveUp();
		// Enemy only
		dungeon.killEnemy(hound);
		dungeon.killEnemy(mage);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// Enemy and Treasure only
		player.moveUp();
		player.equipItem();
		player.moveUp();
		player.equipItem();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// enemy and treasure and boulder
		boulder1.moveDown();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
	}

	// Testing (a and b and c and d) i.e. all goals satisfied
	void testComposite3() {
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
		Exit exit = new Exit(8, 5);
		dungeon.addEntity(exit);
		// (Enemies && Treasure && Boulders && Exit)
		JSONObject goal = new JSONObject("{ \"goal\": \"AND\", \"subgoals\":\n" + "  [ { \"goal\": \"boulders\" },\n"
				+ "    { \"goal\": \"exit\"},\n" + "    { \"goal\": \"enemies\"},\n"
				+ "  	{ \"goal\": \"treasure\"}]\n" + "}");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// enemies only
		dungeon.killEnemy(mage);
		dungeon.killEnemy(hound);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// enemies and treasure
		player.moveUp();
		player.equipItem();
		player.moveUp();
		player.equipItem();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// enemies and treasure and exit
		player.moveUp();
		player.moveDown();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// enemies and treasure and boulders
		boulder1.moveDown();
		boulder2.moveDown();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// and finally all 4 satisfied, exit being last
		player.moveUp();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
	}

	// Testing what happens when player stands on exit while completing other goals
	void testComposite4() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Hound hound = new Hound(dungeon, 4, 5);
		dungeon.addEntity(hound);
		Mage mage = new Mage(dungeon, 2, 6);
		dungeon.addEntity(mage);
		Exit exit = new Exit(2, 4);
		dungeon.addEntity(exit);
		// Enemies && Exit
		JSONObject goal = new JSONObject("{ \"goal\": \"AND\", \"subgoals\":\n" + "  [ { \"goal\": \"exit\" },\n"
				+ "    { \"goal\": \"enemies\"},\n" + "]}");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// Get to exit first
		player.moveLeft();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// Enemies die while player still standing on exit
		dungeon.killEnemy(mage);
		dungeon.killEnemy(hound);
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
	}

	// only one goal satisfied
	void testComposite5() {
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
		Exit exit = new Exit(8, 5);
		dungeon.addEntity(exit);
		// (Enemies && Treasure && Boulders && Exit)
		JSONObject goal = new JSONObject("{ \"goal\": \"OR\", \"subgoals\":\n" + "  [ { \"goal\": \"boulders\" },\n"
				+ "    { \"goal\": \"exit\"},\n" + "    { \"goal\": \"enemies\"},\n"
				+ "  	{ \"goal\": \"treasure\"}]\n" + "}");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// enemies only
		dungeon.killEnemy(mage);
		dungeon.killEnemy(hound);
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
		// add one enemy back
		Hound hound1 = new Hound(dungeon, 1, 1);
		dungeon.addEntity(hound1);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// treasure only
		player.moveUp();
		player.equipItem();
		player.moveUp();
		player.equipItem();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
		// add treasure back
		Treasure gold3 = new Treasure(10, 10);
		dungeon.addEntity(gold3);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// exit only
		player.moveUp();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
		// boulders and exit
		boulder1.moveDown();
		boulder2.moveDown();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
	}

	// Testing ((a || b) || (a || c))
	@Test
	void testComposite6() {
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
		// ((Enemies || Boulders) && (Treasure || Boulders)
		JSONObject goal = new JSONObject(
				"{ \"goal\": \"AND\", \"subgoals\":\n" + "  [ { \"goal\": \"OR\", \"subgoals\":[\n"
						+ "  	{ \"goal\": \"enemies\" },\n" + "  	{ \"goal\": \"boulders\" }\n" + "  ]},\n"
						+ "  	{ \"goal\": \"OR\", \"subgoals\":[\n" + "  	{ \"goal\": \"treasure\" },\n"
						+ "  	{ \"goal\": \"boulders\" }\n" + "  	]\n" + "  	}]\n" + "}");
		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);
		dungeon.setGoalExpression(expr);
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// treasure only
		player.moveUp();
		player.equipItem();
		player.moveUp();
		player.equipItem();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// treasure and boulders
		boulder1.moveDown();
		boulder2.moveDown();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
		// undo boulders
		boulder1.moveUp();
		assertFalse(dungeon.hasWin().getValue(), "Goal checker error");
		// treasure and enemy
		dungeon.killEnemy(hound);
		dungeon.killEnemy(mage);
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
		// all 3 goals
		boulder1.moveDown();
		assertTrue(dungeon.hasWin().getValue(), "Goal checker error");
	}
}
