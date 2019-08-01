package unsw.dungeon;

public class SingleGoal implements GoalExpression {

	private Dungeon dungeon;
	private String goal;

	public SingleGoal(String goal, Dungeon dungeon) {
		this.dungeon = dungeon;
		this.goal = goal;
	}

	@Override
	public boolean isSatisfied() {
		switch (goal) {
		case "exit":
			return dungeon.sameClass(dungeon.getPlayerX(), dungeon.getPlayerX(), "Exit");
		case "treasure":
			return dungeon.completedTreasureGoal();
		case "boulders":
			return dungeon.completedSwitchGoal();
		case "enemies":
			return dungeon.completedEnemyGoal();
		default:
			// unreachable
			return false;
		}
	}

}
