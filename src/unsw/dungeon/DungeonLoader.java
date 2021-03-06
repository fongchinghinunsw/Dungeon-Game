package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

	private JSONObject json;

	public DungeonLoader(String filename) throws FileNotFoundException {
		json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
	}

	/**
	 * Parses the JSON to create a dungeon.
	 * 
	 * @return
	 */
	public Dungeon load() {
		int width = json.getInt("width");
		int height = json.getInt("height");

		Dungeon dungeon = new Dungeon(width, height);

		JSONArray jsonEntities = json.getJSONArray("entities");

		JSONObject goal = json.getJSONObject("goal-condition");

		GoalParser parser = new GoalParser(dungeon);
		GoalExpression expr = parser.parse(goal, null);

		dungeon.setGoalExpression(expr);

		for (int i = 0; i < jsonEntities.length(); i++) {
			loadEntity(dungeon, jsonEntities.getJSONObject(i));
		}

		dungeon.addObservers();
		return dungeon;
	}

	/**
	 * loads all entities in json file
	 * 
	 * @param dungeon to add entities
	 * @param json    to read
	 */
	private void loadEntity(Dungeon dungeon, JSONObject json) {
		String type = json.getString("type");
		int x = json.getInt("x");
		int y = json.getInt("y");

		Entity entity = null;
		switch (type) {
		case "player":
			Player player = new Player(dungeon, x, y);
			dungeon.setPlayer(player);
			onLoad(player);
			entity = player;
			break;
		case "wall":
			Wall wall = new Wall(x, y);
			onLoad(wall);
			entity = wall;
			break;
		case "exit":
			Exit exit = new Exit(x, y);
			onLoad(exit);
			entity = exit;
			break;
		case "boulder":
			Boulder boulder = new Boulder(dungeon, x, y);
			onLoad(boulder);
			entity = boulder;
			break;
		case "key":
			Key key = new Key(dungeon, x, y);
			dungeon.addKey();
			onLoad(key);
			entity = key;
			break;
		case "door":
			Door door = new Door(dungeon, x, y);
			dungeon.addDoor();
			onLoad(door);
			entity = door;
			break;
		case "treasure":
			Treasure treasure = new Treasure(x, y);
			onLoad(treasure);
			entity = treasure;
			break;
		case "potion":
			Potion potion = new Potion(dungeon, x, y);
			potion.isPause().bind(dungeon.isPause());
			onLoad(potion);
			entity = potion;
			break;
		case "bomb":
			Bomb bomb = new Bomb(dungeon, x, y);
			bomb.isPause().bind(dungeon.isPause());
			onLoad(bomb);
			entity = bomb;
			break;
		case "enemy":
			Hound enemy = new Hound(dungeon, x, y);
			enemy.isPause().bind(dungeon.isPause());
			onLoad(enemy);
			entity = enemy;
			break;
		case "hound":
			Hound hound = new Hound(dungeon, x, y);
			hound.isPause().bind(dungeon.isPause());
			onLoad(hound);
			entity = hound;
			break;
		case "mage":
			Mage mage = new Mage(dungeon, x, y);
			mage.isPause().bind(dungeon.isPause());
			onLoad(mage);
			entity = mage;
			break;
		case "sword":
			Sword sword = new Sword(x, y);
			onLoad(sword);
			entity = sword;
			break;
		case "switch":
			Switch floorSwitch = new Switch(dungeon, x, y);
			onLoad(floorSwitch);
			entity = floorSwitch;
			break;
		// TODO Handle other possible entities
		}
		dungeon.addEntity(entity);
	}

	public abstract void onLoad(Entity player);

	public abstract void onLoad(Wall wall);

	public abstract void onLoad(Exit exit);

	public abstract void onLoad(Boulder boulder);

	public abstract void onLoad(Key key);

	public abstract void onLoad(Bomb bomb);

	public abstract void onLoad(Treasure treasure);

	public abstract void onLoad(Sword sword);

	public abstract void onLoad(Potion potion);

	public abstract void onLoad(Hound hound);

	public abstract void onLoad(Mage mage);

	public abstract void onLoad(Switch floorSwitch);

	public abstract void onLoad(Door door);

}
