package rover;

public class MarsRover {

	private Position position;
	private Direction direction;
	private Grid grid = null;

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
				moveRelative(direction.relativeForward);
				break;
			case 'b':
				moveRelative(direction.relativeForward.negative());
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

	public void setGrid(Grid grid) {
		this.grid = grid;
		position = grid.wrap(position);
	}

	private void moveRelative(Position relPosition) {
		Position nextPosition = position.translate(relPosition);
		if (grid != null)
			position = grid.wrap(nextPosition);
		else
			position = nextPosition;
	}
}
