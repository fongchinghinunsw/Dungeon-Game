package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import unsw.dungeon.CompositeGoal.Operator;

public interface GoalExpression {
	public default GoalExpression and(GoalExpression rightOperand) {
		return new CompositeGoal(Operator.AND);
	}

	public default GoalExpression or(GoalExpression rightOperand) {
		return new CompositeGoal(Operator.OR);
	}

	public default GoalExpression no(GoalExpression rightOperand) {
		return new CompositeGoal(null);
	}

	/**
	 * Compute the result of this expression.
	 * 
	 * @return
	 */
	public BooleanProperty isSatisfied();

	public void print();
}
