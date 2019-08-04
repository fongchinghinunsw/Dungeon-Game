package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CompositeGoal implements GoalExpression {
	public enum Operator {
		AND, OR, NULL
	}

	private BooleanProperty satisfied;
	private Operator operator;
	ArrayList<GoalExpression> children = new ArrayList<GoalExpression>();

	/**
	 * composite goal constructor
	 * @param operator, AND,OR or NULL
	 */
	public CompositeGoal(Operator operator) {
		this.operator = operator;
		this.satisfied = new SimpleBooleanProperty(false);
	}

	/**
	 * adds a child into the subgoals
	 * @param child to be added
	 * @return true no matter what happens
	 */
	public boolean add(GoalExpression child) {
		children.add(child);
		return true;
	}

	@Override
	public BooleanProperty isSatisfied() {
		boolean result;

		result = children.get(0).isSatisfied().getValue();

		for (int i = 1; i < children.size(); i++) {
			switch (operator) {
			case AND:
				result = result && children.get(i).isSatisfied().getValue();
				break;
			case OR:
				result = result || children.get(i).isSatisfied().getValue();
				break;
			case NULL:
				// Single goal
				break;
			}
		}
		this.satisfied.set(result);
		return satisfied;
	}

}
