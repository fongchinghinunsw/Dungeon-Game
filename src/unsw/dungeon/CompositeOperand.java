package unsw.dungeon;

public class CompositeOperand implements GoalExpression {
	public enum Operator {
		AND, OR
	}

	private Operator operator;
	private GoalExpression left, right;

	public CompositeOperand(Operator operator, GoalExpression left, GoalExpression right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean evaluate() {
		boolean l = left.evaluate();
		boolean r = right.evaluate();
		switch (operator) {
		case AND:
			return l && r;
		case OR:
			return l || r;
		default:
			return false; // Unreachable unless operator is null
		}
	}
}
