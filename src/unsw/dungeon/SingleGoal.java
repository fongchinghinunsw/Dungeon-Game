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
			return true;
		default:
			// unreachable
			return false;
		}
	}

}
