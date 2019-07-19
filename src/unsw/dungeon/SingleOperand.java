package unsw.dungeon;

public class SingleOperand implements GoalExpression {
	private String goal;

	public SingleOperand(String goal) {
		this.goal = goal;
	}

	@Override
	public boolean evaluate() {
		return false;
	}
}
