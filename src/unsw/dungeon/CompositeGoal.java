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

	public void print() {
		for (int i = 0; i < children.size(); i++) {
			System.out.printf("The operator is %s\n", operator);
			children.get(i).print();
		}
	}

	@Override
	public boolean isSatisfied() {
		boolean result;
		result = children.get(0).isSatisfied();
		children.get(0).print();
		System.out.printf("In CompositeGoal it is %b\n", result);
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
