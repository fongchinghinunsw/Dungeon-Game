/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

	private int width, height;
	private Player player;
	private List<Entity> entities;
	private List<Enemy> enemies;
	private List<Boulder> boulders;
	private List<Switch> switches;
	private int nKeys;
	private int nDoors;
	private int countUntriggeredSwitch;
	private GoalExpression goalExpression;

	public Dungeon(int width, int height) {
		this.width = width;
		this.height = height;
		this.player = null;
		this.entities = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.boulders = new ArrayList<>();
		this.switches = new ArrayList<>();
		this.nKeys = 0;
		this.nDoors = 0;
		this.countUntriggeredSwitch = 0;
	}

	public int getWidth() {
		return width;
	}

	public void addKey() {
		this.nKeys++;
	}

	public int numKey() {
		return this.nKeys;
	}

	public int numDoor() {
		return this.nDoors;
	}

	public void addDoor() {
		this.nDoors++;
	}

	public int getHeight() {
		return height;
	}

	public void addCountUntriggeredSwitch() {
		this.countUntriggeredSwitch++;
	}

	public int countUntriggeredSwitch() {
		int count = countUntriggeredSwitch;
		for (Switch floorSwitch : switches) {
			if (sameClass(floorSwitch.getX(), floorSwitch.getY(), "Boulder")) {
				count--;
			}
		}
		return count;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getPlayerX() {
		return player.getX();
	}

	public int getPlayerY() {
		return player.getY();
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	public void addBoulder(Boulder boulder) {
		boulders.add(boulder);
	}

	public void addSwitch(Switch floorSwitch) {
		switches.add(floorSwitch);
	}

	public void killPlayer() {
		player.die();
		entities.remove(player);
		System.out.println("Player get removed from the dungeon.");
		System.out.println("Game over ~");
	}

	public void killEnemy(Enemy enemy) {
		enemy.die();
		System.out.println("Enemy get removed from the dungeon.");
		entities.remove(enemy);
		enemies.remove(enemy);
	}

	/*
	 * Add observers of each subject.
	 */
	public void addObservers() {
		for (Entity entity : entities) {
			if (!(sameClass(entity.getX(), entity.getY(), "Wall"))) {
				// Add observer for the player
				player.attach((Observer) entity);
				// Add observer for the enemy
				for (Enemy enemy : enemies) {
					enemy.attach((Observer) entity);
				}
			}
			// Add observer for the switch
			if (sameClass(entity.getX(), entity.getY(), "Switch")) {
				for (Boulder boulder : boulders) {
					boulder.attach((Observer) entity);
				}
			}
		}
	}

	public void addObserver(Observer o) {
		Entity entity = (Entity) o;
		if (!(sameClass(entity.getX(), entity.getY(), "Wall"))) {
			// Add observer for the player
			player.attach((Observer) entity);
			// Add observer for the enemy
			for (Enemy enemy : enemies) {
				enemy.attach((Observer) entity);
			}
		}
		// Add observer for the switch
		if (sameClass(entity.getX(), entity.getY(), "Switch")) {
			for (Boulder boulder : boulders) {
				boulder.attach((Observer) entity);
			}
		}
	}

	public void removeObserver(Observer o) {
		for (Entity entity : entities) {
			if (entity instanceof Subject) {
				((Subject) entity).detach(o);
			}
		}
	}

	public void notifyPlayerObservers() {
		player.notifyObservers();
	}

	public void notifyBoulderObservers() {
		for (Boulder boulder : boulders) {
			boulder.notifyObservers();
		}
	}

	/*
	 * Check if there exists an object on the grid belongs to one of the specified
	 * classes.
	 */
	public boolean sameClass(int x, int y, String... className) {
		for (Entity entity : entities) {
			if (entity.getX() == x && entity.getY() == y) {
				for (String name : className) {
					if (name == entity.getClassName()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean hasEquipable(int x, int y) {
		for (Entity entity : entities) {
			if (entity.getX() == x && entity.getY() == y) {
				if (entity instanceof Equipable) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Returns a list which stores all entities in a grid.
	 */
	public ArrayList<Entity> getEntities(int x, int y) {
		ArrayList<Entity> entityList = new ArrayList<>();
		for (Entity entity : entities) {
			if (entity.getX() == x && entity.getY() == y) {
				entityList.add(entity);
			}
		}
		return entityList;
	}

	/*
	 * Remove the entity from the dungeon when it is equipped.
	 */
	public void addEquippedEntity(int x, int y) {
		ArrayList<Entity> entityList = getEntities(x, y);
		for (Entity entity : entityList) {
			if (entity instanceof Equipable) {
				entities.remove(entity);
			}
		}
	}

	public void removeEquippedEntity(int x, int y, String className) {
		Entity entity;
		if (className.equals("Sword")) {
			entity = player.removeSwordInBackPack();
		} else {
			return;
		}
		entity.setX(x);
		entity.setY(y);
		entities.add(entity);
		addObserver((Observer) entity);
	}

	public GoalExpression getGoalExpression() {
		return goalExpression;
	}

	public void setGoalExpression(GoalExpression goalExpression) {
		this.goalExpression = goalExpression;
	}

	public boolean hasWin() {
		return goalExpression.evaluate();
	}

	public boolean canStepOn(int x, int y) {
		ArrayList<Entity> list = this.getEntities(x, y);
		for (Entity e : list) {
			if (e.getClassName().equals("Door") && !((Door) e).isOpen()) {
				return false;
			}
		}
		return true;
	}

	public boolean canPush(int x, int y) {
		ArrayList<Entity> list = this.getEntities(x, y);
		for (Entity e : list) {
			if (e.getClassName().equals("Boulder")) {
				if (player.getX() < x && player.getY() == y) {
					// the player is pushing from the left side.
					return !(sameClass(x + 1, y, "Boulder", "Enemy", "Wall"));
				} else if (player.getX() > x && player.getY() == y) {
					// the player is pushing from the right side.
					return !(sameClass(x - 1, y, "Boulder", "Enemy", "Wall"));
				} else if (player.getX() == x && player.getY() < y) {
					// the player is pushing from the top side.
					return !(sameClass(x, y + 1, "Boulder", "Enemy", "Wall"));
				} else if (player.getX() == x && player.getY() > y) {
					// the player is pushing from the down side.
					return !(sameClass(x, y - 1, "Boulder", "Enemy", "Wall"));
				}
				return false;
			}
		}
		return true;
	}
}
