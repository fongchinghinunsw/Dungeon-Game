package unsw.dungeon;

import java.util.ArrayList;

public class CompositeGoal implements GoalExpression {
	public enum Operator {
		AND, OR
	}

	private Operator operator;
	ArrayList<GoalExpression> children = new ArrayList<GoalExpression>();

	public CompositeGoal(Operator operator, GoalExpression left, GoalExpression right) {
		this.operator = operator;
	}

	@Override
	public boolean isSatisfied() {
		boolean result;
		result = children.get(0).isSatisfied();
		for (int i = 1; i < children.size(); i++) {
			switch (operator) {
			case AND:
				result = result && children.get(i).isSatisfied();
			case OR:
				result = result || children.get(i).isSatisfied();
			}
		}
		return result;
	}

}
