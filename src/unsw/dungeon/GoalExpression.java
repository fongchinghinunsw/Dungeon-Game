package unsw.dungeon;

import unsw.dungeon.CompositeOperand.Operator;

public interface GoalExpression {
	public default GoalExpression and(GoalExpression rightOperand) {
		return new CompositeOperand(Operator.AND, this, rightOperand);
	}

	public default GoalExpression or(GoalExpression rightOperand) {
		return new CompositeOperand(Operator.OR, this, rightOperand);
	}

	/**
	 * Compute the result of this expression.
	 * 
	 * @return
	 */
	public boolean evaluate();
}
