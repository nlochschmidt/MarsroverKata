package rover;

public class Grid {

	public final int width;
	public final int height;

	public Grid(int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Grid needs positive dimensions");
		}
		this.width = width;
		this.height = height;
	}

	public Position wrap(Position origin) {
		return new Position(origin.x % width, origin.y % height);
	}
}
