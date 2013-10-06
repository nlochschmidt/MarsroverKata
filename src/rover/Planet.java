package rover;

import java.util.HashSet;

public class Planet {

	private final Grid grid;
	private final HashSet<Position> obstacles = new HashSet<Position>();

	public Planet(Grid grid) {
		this.grid = grid;
	}

	public Position wrap(Position p) {
		return grid.wrap(p);
	}

	public boolean obstacleAt(Position testPosition) {
		return obstacles.contains(grid.wrap(testPosition));
	}

	public void addObstacle(Position obstacle) {
		obstacles.add(grid.wrap(obstacle));
	}

}
