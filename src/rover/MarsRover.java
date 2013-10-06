package rover;

public class MarsRover {

	public enum Direction {
		N, W, E, S
	}

	private final int x;
	private int y;
	private Direction direction;

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
		for (char command : commands)
			switch (command) {
			case 'f':
				y++;
				break;
			case 'b':
				y--;
				break;
			case 'l':
				turnLeft();
				break;
			}

	}

	private void turnLeft() {
		switch (direction) {
		case N:
			direction = Direction.W;
			break;
		case W:
			direction = Direction.S;
			break;
		case S:
			direction = Direction.E;
			break;
		case E:
			direction = Direction.N;
			break;
		}
	}
}
