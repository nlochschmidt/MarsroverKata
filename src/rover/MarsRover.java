package rover;

public class MarsRover {

	private Position position;
	private Direction direction;

	public MarsRover(int x, int y, Direction d) {
		this.position = new Position(x, y);
		this.direction = d;
	}

	public int x() {
		return position.x;
	}

	public int y() {
		return position.y;
	}

	public Direction direction() {
		return direction;
	}

	public void move(char[] commands) {
		for (char command : commands) {
			switch (command) {
			case 'f':
				position = position.translate(new Position(0, 1));
				break;
			case 'b':
				position = position.translate(new Position(0, -1));
				break;
			case 'l':
				direction = direction.left();
				break;
			case 'r':
				direction = direction.right();
				break;
			}
		}
	}
}
