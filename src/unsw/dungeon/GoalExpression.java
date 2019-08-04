package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import unsw.dungeon.CompositeGoal.Operator;

/**
 * goal expression representing the goals in the game
 * 
 * @author z5211173
 *
 */
public interface GoalExpression {
	/**
	 * sets up an AND expression
	 * 
	 * @param rightOperand
	 * @return
	 */
	public default GoalExpression and(GoalExpression rightOperand) {
		return new CompositeGoal(Operator.AND);
	}

	/**
	 * sets up an OR expression
	 * 
	 * @param rightOperand
	 * @return
	 */
	public default GoalExpression or(GoalExpression rightOperand) {
		return new CompositeGoal(Operator.OR);
	}

	/**
	 * sets up a single expression
	 * 
	 * @param rightOperand
	 * @return
	 */
	public default GoalExpression no(GoalExpression rightOperand) {
		return new CompositeGoal(null);
	}

	/**
	 * Compute the result of this expression.
	 * 
	 * @return
	 */
	public BooleanProperty isSatisfied();

}
