package unsw.dungeon;

import java.util.ArrayList;

public class CompositeOperand implements GoalExpression {
	public enum Operator {
		AND, OR
	}

	private Operator operator;
	ArrayList<GoalExpression> children = new ArrayList<GoalExpression>();

	public CompositeOperand(Operator operator, GoalExpression left, GoalExpression right) {
		this.operator = operator;
	}

	@Override
	public boolean evaluate() {
		boolean result;
		for (int i = 0; i < children.size(); i++) {
			// result =
		}
		return false;
	}
}
