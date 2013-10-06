package rover;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PositionTest {
	@Test
	public void createPosition() {
		Position p = new Position(2, 3);
		assertThat(p.x, equalTo(2));
		assertThat(p.y, equalTo(3));
	}

	@Test
	public void translatePosition() {
		Position p = new Position(2, 3);
		Position rel = new Position(1, 2);
		Position result = p.translate(rel);
		assertThat(result.x, equalTo(3));
		assertThat(result.y, equalTo(5));
	}
}
