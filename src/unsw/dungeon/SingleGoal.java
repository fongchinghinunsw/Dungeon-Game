package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SingleGoal implements GoalExpression {

	private Dungeon dungeon;
	private String goal;
	private BooleanProperty satisfied;

	public SingleGoal(String goal, Dungeon dungeon) {
		this.dungeon = dungeon;
		this.goal = goal;
		satisfied = new SimpleBooleanProperty(false);
	}

	public void print() {
		System.out.printf("Single goal: %s\n", goal);
	}

	@Override
	public BooleanProperty isSatisfied() {
		switch (goal) {
		case "exit":
			satisfied.set(dungeon.sameClass(dungeon.getPlayerX(), dungeon.getPlayerY(), "Exit"));
			break;
		case "treasure":
			satisfied.set(dungeon.completedTreasureGoal());
			break;
		case "boulders":
			satisfied.set(dungeon.completedSwitchGoal());
			break;
		case "enemies":
			satisfied.set(dungeon.completedEnemyGoal());
			break;
		default:
			// unreachable
			return null;
		}
		return satisfied;
	}

}
