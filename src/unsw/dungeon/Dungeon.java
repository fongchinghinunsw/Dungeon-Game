/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;

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
	private int nKeys;
	private int nDoors;
	private GoalExpression goalExpression;
	private BooleanProperty pause;
	private Timeline goalChecker;

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
		this.nKeys = 0;
		this.nDoors = 0;
		this.pause = new SimpleBooleanProperty(false);
		this.goalChecker = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
			if (!pause.getValue()) {
				if (this.hasWin().getValue()) {
					goalChecker.stop();
				}
			}
		}));
		goalChecker.setCycleCount(Timeline.INDEFINITE);
		goalChecker.play();
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
	 * getter method for height
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
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
	 * increases total number of doors
	 */
	public void addDoor() {
		this.nDoors++;
	}

	/**
	 * getter method for nDoors
	 * 
	 * @return nDoors
	 */
	public int numDoor() {
		return this.nDoors;
	}

	/*
	 * return true if the floor switch goal has been completed
	 */
	public boolean completedSwitchGoal() {
		return countUntriggeredSwitch() == 0 ? true : false;
	}

	/**
	 * 
	 * @return number of untriggered switches
	 */
	public int countUntriggeredSwitch() {
		int count = 0;
		for (Entity entity : entities) {
			if (entity.getClassName().equals("Switch")) {
				if (!(sameClass(entity.getX(), entity.getY(), "Boulder"))) {
					count++;
				}
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
	 * 
	 * @return number of untriggered switches
	 */
	public int countRemainingTreasure() {
		int count = 0;
		for (Entity entity : entities) {
			if (entity.getClassName().equals("Treasure")) {
				count++;
			}
		}
		return count;
	}

	public boolean completedEnemyGoal() {
		// cannot win the game if player's dead
		if (!player.isAlive().getValue()) {
			return false;
		}
		return countRemainingEnemy() == 0 ? true : false;
	}

	public int countRemainingEnemy() {
		int count = 0;
		for (Entity entity : entities) {
			if (entity.getClassName().equals("Enemy")) {
				Enemy enemy = (Enemy) entity;
				if (enemy.isAlive().getValue()) {
					count++;
				}
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
	 * kills the player
	 */
	public void killPlayer() {
		player.die();
		entities.remove(player);
	}

	/**
	 * kills a given enemy
	 * 
	 * @param enemy to be killed
	 */
	public void killEnemy(Enemy enemy) {
		enemy.die();
		entities.remove(enemy);
	}

	/**
	 * destroys a given boulder
	 * 
	 * @param boulder to be destroyed
	 */
	public void destroyBoulder(Boulder boulder) {
		entities.remove(boulder);
	}

	/**
	 * adds observer for everyone
	 */
	public void addObservers() {
		for (Entity entity : entities) {
			if (!(sameClass(entity.getX(), entity.getY(), "Wall"))) {
				// Add observer for the player
				player.attach((Observer) entity);
				// Add observer for the enemy
				addObserverOfClass("Enemy", (Observer) entity);
				addObserverOfClass("Bomb", (Observer) entity);
			}
			// Add observer for the switch
			if (sameClass(entity.getX(), entity.getY(), "Switch")) {
				addObserverOfClass("Boulder", (Observer) entity);
			}
		}
	}

	/**
	 * adds observer of a specific class
	 * 
	 * @param className
	 * @param observer  to be added
	 */
	public void addObserverOfClass(String className, Observer observer) {
		for (Entity entity : entities) {
			if (entity.getClassName().equals(className)) {
				Subject sub = (Subject) entity;
				sub.attach(observer);
			}
		}
	}

	/**
	 * Adds a specific observer for every entity(except walls)
	 * 
	 * @param o observer to be added
	 */
	public void addObserver(Observer o) {
		Entity entity = (Entity) o;
		if (!(sameClass(entity.getX(), entity.getY(), "Wall"))) {
			// Add observer for the player
			player.attach((Observer) entity);
			// Add observer for the enemy
			addObserverOfClass("Enemy", (Observer) entity);
		}
		// Add observer for the switch
		if (sameClass(entity.getX(), entity.getY(), "Switch")) {
			addObserverOfClass("Boulder", (Observer) entity);
		}
	}

	/**
	 * removes a given observer
	 * 
	 * @param observer to be removed
	 */
	public void removeObserver(Observer o) {
		for (Entity entity : entities) {
			if (entity instanceof Subject) {
				((Subject) entity).detach(o);
			}
		}
	}

	/**
	 * notifies all observers of player
	 */
	public void notifyPlayerObservers() {
		player.notifyObservers();
	}

	/**
	 * checks if there is an object in a given grid that is of a given class
	 * 
	 * @param x         coordinate of given grid
	 * @param y         coordinate of given grid
	 * @param className to search
	 * @return true if found
	 */
	public boolean sameClass(int x, int y, String className) {
		return sameClass(x, y, new HashSet<String>(Arrays.asList(className)));
	}

	public boolean sameClass(int x, int y, Set<String> classNames) {
		for (Entity entity : entities) {
			if (entity.getX() == x && entity.getY() == y) {
				for (String name : classNames) {
					if (name.equals(entity.getClassName())) {
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

	/**
	 * gets a list of all entities in a given grid
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return list
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

	/**
	 * removes a equipable from the dungeon when its equipped
	 * 
	 * @param x coordinate to look for
	 * @param y coordinate to look for
	 */
	public void removeEquippedEntityFromDungeon(int x, int y) {
		ArrayList<Entity> entityList = getEntities(x, y);
		for (Entity entity : entityList) {
			if (entity instanceof Equipable) {
				entities.remove(entity);
			}
		}
	}

	/**
	 * removes an entity from the list of entities
	 * 
	 * @param entity to be removed
	 */
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	/**
	 * removes an equipped item of a given class from the backpack
	 * 
	 * @param x         of player
	 * @param y         of player
	 * @param className of item-to-remove
	 */
	public void removeEquippedEntityFromBackPack(int x, int y, String className) {
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

	/**
	 * getter for goalExpression
	 * 
	 * @return goalExpression
	 */
	public GoalExpression getGoalExpression() {
		return goalExpression;
	}

	/**
	 * setter for goalExpression
	 * 
	 * @param goalExpression to be set
	 */
	public void setGoalExpression(GoalExpression goalExpression) {
		this.goalExpression = goalExpression;
	}

	/**
	 * checks if player has satisfied all goals
	 * 
	 * @return BooleanProperty containing goal status
	 */
	public BooleanProperty hasWin() {
		return goalExpression.isSatisfied();
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
				Set<String> canPushSet = new HashSet<String>(Arrays.asList("Boulder", "Enemy", "Wall", "Door"));
				if (player.getX() < x && player.getY() == y) {
					// the player is pushing from the left side.
					return !(sameClass(x + 1, y, canPushSet));
				} else if (player.getX() > x && player.getY() == y) {
					// the player is pushing from the right side.
					return !(sameClass(x - 1, y, canPushSet));
				} else if (player.getX() == x && player.getY() < y) {
					// the player is pushing from the top side.
					return !(sameClass(x, y + 1, canPushSet));
				} else if (player.getX() == x && player.getY() > y) {
					// the player is pushing from the down side.
					return !(sameClass(x, y - 1, canPushSet));
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
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Sets up a graph for the dungeon(To be used by towardsPlayerPath, in a DFS
	 * pathfinding algo)
	 * 
	 * @return List of edges
	 */
	public List<String> dungeonGraph() {
		List<String> edges = new ArrayList<>();
		Set<String> cantMoveSet = new HashSet<>();
		cantMoveSet.add("Wall");
		cantMoveSet.add("Boulder");
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				String from = Integer.toString(i) + "," + Integer.toString(j);
				if (!(sameClass(i, j, cantMoveSet)) && canStepOn(i, j)) {
					if (i - 1 >= 0 && !(sameClass(i - 1, j, cantMoveSet) && canStepOn(i, j))) {
						String to = Integer.toString(i - 1) + "," + Integer.toString(j);
						edges.add(String.format("%s->%s", from, to));
					}
					if (i + 1 < this.width && !(sameClass(i + 1, j, cantMoveSet) && canStepOn(i, j))) {
						String to = Integer.toString(i + 1) + "," + Integer.toString(j);
						edges.add(String.format("%s->%s", from, to));
					}
					if (j - 1 >= 0 && !(sameClass(i, j - 1, cantMoveSet) && canStepOn(i, j))) {
						String to = Integer.toString(i) + "," + Integer.toString(j - 1);
						edges.add(String.format("%s->%s", from, to));
					}
					if (j + 1 < this.height && !(sameClass(i, j + 1, cantMoveSet) && canStepOn(i, j))) {
						String to = Integer.toString(i) + "," + Integer.toString(j + 1);
						edges.add(String.format("%s->%s", from, to));
					}
				}
			}
		}
		return edges;
	}

	/**
	 * finds a path towards the player
	 * 
	 * @param x     coordinate of enemy
	 * @param y     coordinate of enemy
	 * @param destX coordinate of player
	 * @param destY coordinate of player
	 * @return List containing the paths(Strings like "Left","Up", etc.)
	 */
	public List<String> towardsPlayerPath(int x, int y, int destX, int destY) {
		List<String> edges = dungeonGraph();
		List<String> movements = new ArrayList<>();
		Map<String, Boolean> hasVisited = new HashMap<>();
		Set<String> cantMoveSet = new HashSet<>();
		cantMoveSet.add("Wall");
		cantMoveSet.add("Boulder");
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				if (!(sameClass(i, j, cantMoveSet) && canStepOn(i, j))) {
					hasVisited.put(String.format("%d,%d", i, j), false);
				}
			}
		}
		findPath(edges, movements, hasVisited, String.format("%d,%d", x, y), String.format("%d,%d", destX, destY));
		return movements;

	}

	/**
	 * Finds a path towards the player
	 * 
	 * @param edges      in the graph
	 * @param movements  to execute
	 * @param hasVisited records history of visited nodes
	 * @param curr       current location in a string, e.g. "3,4"
	 * @param dest       location of player in a string
	 * @return true if path can be found
	 */
	public boolean findPath(List<String> edges, List<String> movements, Map<String, Boolean> hasVisited, String curr,
			String dest) {
		hasVisited.replace(curr, true);
		for (String edge : edges) {
			String[] locations = edge.split("->");
			if (locations[0].equals(curr)) {
				int fromX = Integer.parseInt(locations[0].split(",")[0]);
				int fromY = Integer.parseInt(locations[0].split(",")[1]);
				int toX = Integer.parseInt(locations[1].split(",")[0]);
				int toY = Integer.parseInt(locations[1].split(",")[1]);
				if (locations[1].equals(dest)) {
					if (fromX < toX) {
						movements.add("RIGHT");
					} else if (fromY < toY) {
						movements.add("DOWN");
					} else if (fromX > toX) {
						movements.add("LEFT");
					} else if (fromY > toY) {
						movements.add("UP");
					}
					return true;
				} else if (hasVisited.get(locations[1]) == false) {

					if (findPath(edges, movements, hasVisited, locations[1], dest)) {
						if (fromX < toX) {
							movements.add("RIGHT");
						} else if (fromY < toY) {
							movements.add("DOWN");
						} else if (fromX > toX) {
							movements.add("LEFT");
						} else if (fromY > toY) {
							movements.add("UP");
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * get number of items in the backpack
	 * 
	 * @return mapping of items to amounts
	 */
	public Map<String, Integer> getItemsInBackpack() {
		return player.getNumberOfItemsInBackpack();
	}

	/**
	 * pauses the game
	 */
	public void pauseGame() {
		this.pause.setValue(true);
	}

	/**
	 * resumes the game
	 */
	public void resumeGame() {

		this.pause.setValue(false);
	}

	/**
	 * checks if is paused
	 * 
	 * @return true BoolPpty if paused
	 */
	public BooleanProperty isPause() {
		return this.pause;
	}
}
