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

}
