package rover;

public class Position {

	public final int x;
	public final int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position translate(Position p) {
		return new Position(x + p.x, y + p.y);
	}

}
