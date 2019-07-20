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
	private List<Entity> entities;
	private GoalExpression goalExpression;
	private Player player;
	private List<Enemy> enemies;

	public Dungeon(int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = new ArrayList<>();
		this.player = null;
		this.enemies = new ArrayList<>();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	public boolean killPlayer() {
		return player.die();
	}

	public void kilEnemy(Enemy enemy) {
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
	public ArrayList<Entity> getEntity(int x, int y) {
		ArrayList<Entity> entityList = new ArrayList<>();
		for (Entity entity : entities) {
			if (entity.getX() == x && entity.getY() == y) {
				entityList.add(entity);
			}
		}
		return entityList;
	}

	public void addEquippedEntity(int x, int y) {
		ArrayList<Entity> entityList = getEntity(x, y);
		for (Entity entity : entityList) {
			if (entity instanceof Equipable) {
				entities.remove(entity);
			}
		}
	}

	public void removeEquippedEntity(int x, int y, String className) {
		Entity entity = player.removeSwordInBackPack();
		entity.setX(x);
		entity.setY(y);
		entities.add(entity);

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
}
