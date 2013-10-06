package rover;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PlanetTest {

	@Test
	public void testPlanetUsesGrid() {
		Position p = new Position(0, 0);
		Grid grid = mock(Grid.class);
		when(grid.wrap(p)).thenReturn(p);

		Planet planet = new Planet(grid);
		planet.wrap(p);

		verify(grid, only()).wrap(p);
	}
}
