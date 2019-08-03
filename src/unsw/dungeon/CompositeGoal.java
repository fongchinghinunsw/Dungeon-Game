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

	public CompositeGoal(Operator operator) {
		this.operator = operator;
		this.satisfied = new SimpleBooleanProperty(false);
	}

	public boolean add(GoalExpression child) {
		children.add(child);
		return true;
	}

	public void print() {
		System.out.println(children.size());
		for (int i = 0; i < children.size(); i++) {
			System.out.printf("The operator is %s\n", operator);
			children.get(i).print();
		}
	}

	@Override
	public BooleanProperty isSatisfied() {
		boolean result;
		result = children.get(0).isSatisfied().getValue();
		children.get(0).print();
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
