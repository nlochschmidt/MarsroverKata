package rover;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GridTest {

	@Test
	public void gridHasDimensions() {
		Grid grid = new Grid(100, 100);
		assertThat(grid.width, equalTo(100));
		assertThat(grid.height, equalTo(100));
	}

	@Test(expected = IllegalArgumentException.class)
	public void gridNeedsNonNegativeDimensions() {
		try {
			new Grid(-1, 100);
			fail();
		} catch (IllegalArgumentException iae) {
		}
		try {
			new Grid(100, -1);
			fail();
		} catch (IllegalArgumentException iae) {
		}
		new Grid(-1, -1);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void gridNeedsDimensionsLargerZero() {
		try {
			new Grid(0, 100);
			fail();
		} catch (IllegalArgumentException iae) {
		}
		try {
			new Grid(100, 0);
			fail();
		} catch (IllegalArgumentException iae) {
		}
		new Grid(0, 0);
		fail();
	}

	@Test
	public void calculateWrappedLargePosition() {
		Grid grid = new Grid(100, 100);
		Position[] wrapFrom = { p(0, 100), p(100, 0), p(100, 100), p(0, 0) };
		Position expected = p(0, 0);
		for (Position origin : wrapFrom)
			assertThat(grid.wrap(origin), equalTo(expected));
	}

	@Test
	public void calculateWrappedNegativePosition() {
		Grid grid = new Grid(100, 100);
		Position[] wrapFrom = { p(99, -1), p(-1, 99), p(-1, -1), p(99, 99) };
		Position expected = p(99, 99);
		for (Position origin : wrapFrom)
			assertThat(grid.wrap(origin), equalTo(expected));
	}

	private Position p(int x, int y) {
		return new Position(x, y);
	}

}
