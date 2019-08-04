package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.CompositeGoal.Operator;

/**
 * Goal parser processes the json object of goal and records it into recursive
 * GoalExpression objects
 * 
 * @author z5211173
 *
 */
public class GoalParser {
	private Dungeon dungeon;

	/**
	 * constructor for goal parser
	 * 
	 * @param dungeon
	 */
	public GoalParser(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	/**
	 * parses the json object to construct goal expression tree
	 * 
	 * @param obj
	 * @param c_0
	 * @return
	 */
	public CompositeGoal parse(JSONObject obj, CompositeGoal c_0) {
		CompositeGoal c_1;
		if (obj.getString("goal").equals("AND") || obj.getString("goal").equals("OR")) {
			JSONArray array = obj.getJSONArray("subgoals");
			if (obj.getString("goal").equals("AND")) {
				c_1 = new CompositeGoal(Operator.AND);
			} else {
				c_1 = new CompositeGoal(Operator.OR);
			}
			for (int i = 0; i < array.length(); i++) {
				JSONObject tmp = array.getJSONObject(i);
				GoalExpression c_2;
				if (tmp.getString("goal").equals("AND")) {
					c_2 = parse(tmp, c_1);
				} else if (tmp.getString("goal").equals("OR")) {
					c_2 = parse(tmp, c_1);
				} else {
					c_2 = new SingleGoal(tmp.getString("goal"), this.dungeon);
				}
				c_1.add(c_2);
			}
		} else {
			c_1 = new CompositeGoal(Operator.NULL);
			SingleGoal s_1 = new SingleGoal(obj.getString("goal"), this.dungeon);
			c_1.add(s_1);
		}
		if (c_0 != null) {
			c_0.add(c_1);
		}
		return c_1;
	}
}
