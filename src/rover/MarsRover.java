package rover;

public class MarsRover {

	public enum Direction {
		N, W, E, S
	}

	private final int x;
	private int y;
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

	public void move(char[] commands) {
		if (commands[0] == 'f') {
			y++;
		} else if (commands[0] == 'b') {
			y--;
		}

	}
}
