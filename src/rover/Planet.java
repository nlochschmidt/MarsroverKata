package rover;


public class Planet {

	private final Grid grid;

	public Planet(Grid grid) {
		this.grid = grid;
	}

	public Position wrap(Position p) {
		return grid.wrap(p);
	}

}
