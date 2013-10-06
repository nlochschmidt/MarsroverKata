package rover;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
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

	@Test
	public void addObstacle() {
		Position obstacle = new Position(2, 2);
		Planet planet = new Planet(new Grid(100, 100));
		assertThat(planet.obstacleAt(obstacle), is(false));
		planet.addObstacle(obstacle);
		assertThat(planet.obstacleAt(obstacle), is(true));
	}

	@Test
	public void testAddObstacleOutOfGrid() {
		Position obstacle = new Position(150, 150);
		Position realObstacle = new Position(50, 50);
		Grid grid = mock(Grid.class);
		when(grid.wrap(obstacle)).thenReturn(realObstacle);
		when(grid.wrap(realObstacle)).thenReturn(realObstacle);

		Planet planet = new Planet(grid);
		assertThat(planet.obstacleAt(obstacle), is(false));
		assertThat(planet.obstacleAt(realObstacle), is(false));
		planet.addObstacle(obstacle);
		assertThat(planet.obstacleAt(obstacle), is(true));
		assertThat(planet.obstacleAt(realObstacle), is(true));

		verify(grid, times(3)).wrap(obstacle);
		verify(grid, times(2)).wrap(realObstacle);
	}

	@Test
	public void planetFactoryFunction() {
		Planet planet = Planet.createWithDimensions(100, 100);
		assertThat(planet.wrap(new Position(100, 100)), equalTo(new Position(0,
				0)));
	}
}
