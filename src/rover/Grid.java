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
		return new Position(mod(origin.x, width), mod(origin.y, height));
	}

	// Java has strange mod definition where negative number stay negative
	// http://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers
	private static int mod(int x, int m) {
		return (x % m + m) % m;
	}
}
