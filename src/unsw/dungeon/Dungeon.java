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
	private List<Treasure> treasures;
	private List<Bomb> bombs;
	private int nKeys;
	private int nDoors;
	private int countUntriggeredSwitch;
	private int countRemainingTreasure;
	private GoalExpression goalExpression;

	/**
	 * constructor for dungeon
	 * 
	 * @param width
	 * @param height
	 */
	public Dungeon(int width, int height) {
		this.width = width;
		this.height = height;
		this.player = null;
		this.entities = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.boulders = new ArrayList<>();
		this.switches = new ArrayList<>();
		this.treasures = new ArrayList<>();
		this.bombs = new ArrayList<>();
		this.nKeys = 0;
		this.nDoors = 0;
		this.countUntriggeredSwitch = 0;
	}

	/**
	 * getter method for width
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * increases total number of keys
	 */
	public void addKey() {
		this.nKeys++;
	}

	/**
	 * getter method for nKeys
	 * 
	 * @return nKeys
	 */
	public int numKey() {
		return this.nKeys;
	}

	/**
	 * getter method for nDoors
	 * 
	 * @return nDoors
	 */
	public int numDoor() {
		return this.nDoors;
	}

	/**
	 * increases total number of doors
	 */
	public void addDoor() {
		this.nDoors++;
	}

	/**
	 * getter method for height
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * return true if the floor switch goal has been completed
	 */
	public boolean completedSwitchGoal() {
		return countUntriggeredSwitch() == 0 ? true : false;
	}

	/**
	 * increase untriggered switches by 1
	 */
	public void addCountUntriggeredSwitch() {
		this.countUntriggeredSwitch++;
	}

	/**
	 * 
	 * @return number of untriggered switches
	 */
	public int countUntriggeredSwitch() {
		int count = countUntriggeredSwitch;
		for (Switch floorSwitch : switches) {
			if (sameClass(floorSwitch.getX(), floorSwitch.getY(), "Boulder")) {
				count--;
			}
		}
		return count;
	}

	/*
	 * return true if the treasure goal has been completed
	 */
	public boolean completedTreasureGoal() {
		return countRemainingTreasure() == 0 ? true : false;
	}

	/**
	 * increase unpicked treasure by 1
	 */
	public void addCountRemainingTreasure() {
		this.countRemainingTreasure++;
	}

	/**
	 * 
	 * @return number of untriggered switches
	 */
	public int countRemainingTreasure() {
		int count = countRemainingTreasure;
		for (Treasure treasure : treasures) {
			if (!(sameClass(treasure.getX(), treasure.getY(), "Treasure"))) {
				count--;
			}
		}
		return count;
	}

	/**
	 * getter method for player
	 * 
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * setter method for player
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * getter method for player's x
	 * 
	 * @return player's x
	 */
	public int getPlayerX() {
		return player.getX();
	}

	/**
	 * getter method for player's y
	 * 
	 * @return player's y
	 */
	public int getPlayerY() {
		return player.getY();
	}

	/**
	 * adds an entity to entities
	 * 
	 * @param entity to be added
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	/**
	 * adds an enemy to enemies
	 * 
	 * @param enemy to be added
	 */
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	/**
	 * adds a bomb to bombs
	 * 
	 * @param bomb to be added
	 */
	public void addBomb(Bomb bomb) {
		bombs.add(bomb);
	}

	/**
	 * adds a boulder to boulders
	 * 
	 * @param boulder to be added
	 */
	public void addBoulder(Boulder boulder) {
		boulders.add(boulder);
	}

	/**
	 * adds a switch to switches
	 * 
	 * @param floorSwitch to be added
	 */
	public void addSwitch(Switch floorSwitch) {
		switches.add(floorSwitch);
	}

	public void addTreasure(Treasure treasure) {
		treasures.add(treasure);
	}

	/**
	 * kills the player
	 */
	public void killPlayer() {
		player.die();
		entities.remove(player);
		System.out.println("Player get removed from the dungeon.");
		System.out.println("Game over ~");
	}

	/**
	 * kills a given enemy
	 * 
	 * @param enemy to be killed
	 */
	public void killEnemy(Enemy enemy) {
		enemy.die();
		System.out.println("Enemy get removed from the dungeon.");
		entities.remove(enemy);
		enemies.remove(enemy);
	}

	/**
	 * destroys a given boulder
	 * 
	 * @param boulder to be destroyed
	 */
	public void destroyBoulder(Boulder boulder) {
		System.out.println("Boulder destroyed");
		entities.remove(boulder);
		boulders.remove(boulder);
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
				for (Bomb bomb : bombs) {
					bomb.attach((Observer) entity);
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

	/**
	 * checks if there's an equipable in given place
	 * 
	 * @param x
	 * @param y
	 * @return true if found
	 */
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

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	/**
	 * removes an equipped item of a given class
	 * 
	 * @param x         of player
	 * @param y         of player
	 * @param className of item-to-remove
	 */
	public void removeEquippedEntity(int x, int y, String className) {
		Entity entity;
		if (className.equals("Sword")) {
			entity = player.removeSwordInBackPack();
		} else if (className.equals("Bomb")) {
			entity = player.removeBombInBackpack();
		} else if (className.equals("Key")) {
			entity = player.removeKeyInBackpack();
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

	/**
	 * checks if the player can go through a grid
	 * 
	 * @param x of door
	 * @param y of door
	 * @return true if door's open
	 */
	public boolean canStepOn(int x, int y) {
		ArrayList<Entity> list = this.getEntities(x, y);
		for (Entity e : list) {
			if (e.getClassName().equals("Door") && !((Door) e).isOpen()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * checks if the player can push the boulder
	 * 
	 * @param x of boulder
	 * @param y of boulder
	 * @return true if can be pushed
	 */
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

	/**
	 * checks if there's a lit bomb in a given position
	 * 
	 * @param x of player
	 * @param y of player
	 * @return true if there is already a lit bomb
	 */
	public boolean hasBomb(int x, int y) {
		for (Entity entity : entities) {
			if (entity.getX() == x && entity.getY() == y) {
				if (entity.getClassName().equals("Bomb") && ((Bomb) entity).lit()) {
					System.out.println("There's already a lit bomb here.");
					return true;
				}
			}
		}
		return false;
	}
}
