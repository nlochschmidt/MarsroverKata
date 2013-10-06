package rover;

public class MarsRover {

	private Position position;
	private Direction direction;
	private Planet planet = null;

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

	public void move(char[] commands) throws ObstacleEncounteredException {
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

	public void landOn(Planet planet) {
		this.planet = planet;
		try {
			moveAbsolute(position);
		} catch (ObstacleEncounteredException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private void moveAbsolute(Position nextPosition)
			throws ObstacleEncounteredException {
		if (planet != null) {
			if (planet.obstacleAt(nextPosition)) {
				throw new ObstacleEncounteredException(
						String.format("Obstacle at %s, staying on %s",
								nextPosition, position));
			} else {
				position = planet.wrap(nextPosition);
			}
		} else {
			position = nextPosition;
		}
	}

	private void moveRelative(Position relativePosition)
			throws ObstacleEncounteredException {
		moveAbsolute(position.translate(relativePosition));

	}

	public class ObstacleEncounteredException extends Exception {
		private static final long serialVersionUID = 4719036424670083482L;

		public ObstacleEncounteredException(String msg) {
			super(msg);
		}
	}
}
