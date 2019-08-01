package unsw.dungeon;

import java.util.ArrayList;

public class CompositeGoal implements GoalExpression {
	public enum Operator {
		AND, OR, NULL
	}

	private Operator operator;
	ArrayList<GoalExpression> children = new ArrayList<GoalExpression>();

	public CompositeGoal(Operator operator) {
		this.operator = operator;
	}

	public boolean add(GoalExpression child) {
		children.add(child);
		return true;
	}

	@Override
	public boolean isSatisfied() {
		boolean result;
		result = children.get(0).isSatisfied();
		for (int i = 1; i < children.size(); i++) {
			switch (operator) {
			case AND:
				result = result && children.get(i).isSatisfied();
				break;
			case OR:
				result = result || children.get(i).isSatisfied();
				break;
			case NULL:
				// Single goal
				break;
			}
		}
		return result;
	}

}
