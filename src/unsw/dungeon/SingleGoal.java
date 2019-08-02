package unsw.dungeon;

public class SingleGoal implements GoalExpression {

	private Dungeon dungeon;
	private String goal;

	public SingleGoal(String goal, Dungeon dungeon) {
		this.dungeon = dungeon;
		this.goal = goal;
	}

	public void print() {
		System.out.printf("Single goal: %s\n", goal);
	}

	@Override
	public boolean isSatisfied() {
		switch (goal) {
		case "exit":
			// System.out.println(dungeon.sameClass(dungeon.getPlayerX(),
			// dungeon.getPlayerY(), "Exit"));
			return dungeon.sameClass(dungeon.getPlayerX(), dungeon.getPlayerY(), "Exit");
		case "treasure":
			return dungeon.completedTreasureGoal();
		case "boulders":
			return dungeon.completedSwitchGoal();
		case "enemies":
			return dungeon.completedEnemyGoal();
		default:
			// unreachable
			return true;
		}
	}

}
