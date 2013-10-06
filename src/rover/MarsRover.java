package rover;

public class MarsRover {

	public enum Direction {
		N, W, E, S
	}

	private final int x;
	private final int y;
	private final Direction direction;

	public MarsRover(int x, int y, Direction d) {
		this.x = x;
		this.y = y;
		this.direction = d;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public Direction direction() {
		return direction;
	}
}
