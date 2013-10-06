package rover;

public enum Direction {
	// Direction(leftTurn, rightTurn)
	N("W", "E", new Position(0, 1)), W("S", "N", new Position(-1, 0)), E("N",
			"S", new Position(1, 0)), S("E", "W", new Position(0, -1));

	final String left;
	final String right;
	final Position relativeForward;

	public Direction left() {
		return Direction.valueOf(left);
	}

	public Direction right() {
		return Direction.valueOf(right);
	}

	private Direction(String left, String right, Position relativeForward) {
		this.left = left;
		this.right = right;
		this.relativeForward = relativeForward;
	}
}