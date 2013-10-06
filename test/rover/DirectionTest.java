package rover;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static rover.Direction.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DirectionTest {

	@Test
	public void directionHasLeft() {
		Direction[] current = { N, W, S, E };
		Direction[] left = { W, S, E, N };

		for (int i = 0; i < current.length; i++) {
			assertSame(current[i].left(), left[i]);
		}
	}

	@Test
	public void directionHasRight() {
		Direction[] current = { N, E, S, W };
		Direction[] left = { E, S, W, N };

		for (int i = 0; i < current.length; i++) {
			assertSame(current[i].right(), left[i]);
		}
	}

	@Test
	public void directionHasRelativePositions() {
		Direction[] current = { N, W, S, E };
		Position[] relative = { new Position(0, 1), new Position(-1, 0),
				new Position(0, -1), new Position(1, 0) };
		for (int i = 0; i < current.length; i++) {
			assertThat(current[i].relativeForward, equalTo(relative[i]));
		}
	}
}
