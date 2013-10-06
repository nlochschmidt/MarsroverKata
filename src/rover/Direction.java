package rover;

public enum Direction {
	// Direction(leftTurn, rightTurn)
	N("W", "E"), W("S", "N"), E("N", "S"), S("E", "W");

	String left;
	String right;

	public Direction left() {
		return Direction.valueOf(left);
	}

	public Direction right() {
		return Direction.valueOf(right);
	}

	private Direction(String left, String right) {
		this.left = left;
		this.right = right;
	}

}