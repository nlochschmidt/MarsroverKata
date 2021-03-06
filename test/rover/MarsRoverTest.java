package rover;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import rover.MarsRover.ObstacleEncounteredException;

/*
 Develop an api that moves a rover around on a grid.
 You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
 The rover receives a character array of commands.
 Implement commands that move the rover forward/backward (f,b).
 Implement commands that turn the rover left/right (l,r).
 Implement wrapping from one edge of the grid to another. (planets are spheres after all)
 Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle.
 Example: The rover is on a 100x100 grid at location (0, 0) and facing NORTH. The rover is given the commands "ffrff" and should end up at (2, 2)
 Tips: use multiple classes and TDD
 - See more at: http://craftsmanship.sv.cmu.edu/katas/mars-rover-kata#sthash.RxxKxf56.dpuf
 */

@RunWith(JUnit4.class)
public class MarsRoverTest {

	@Test
	public void roverHasStartingPosition() {
		MarsRover rover = createMarsRover(0, 0, Direction.N);
		assertPosition(rover, 0, 0);

		rover = createMarsRover(10, 15, Direction.N);
		assertPosition(rover, 10, 15);
	}

	@Test
	public void roverHasStartingDirection() {
		MarsRover rover = createMarsRover(0, 0, Direction.N);
		assertDirection(rover, Direction.N);
	}

	@Test
	public void driveRoverForwardOnce() {
		MarsRover rover = createMarsRover(0, 0, Direction.N);
		moveRover(rover, "f");
		assertPosition(rover, 0, 1);
	}

	@Test
	public void driveRoverBackwardOnce() {
		MarsRover rover = createMarsRover(0, 1, Direction.N);
		moveRover(rover, "b");
		assertPosition(rover, 0, 0);
	}

	@Test
	public void driveRoverForwardBackward() {
		MarsRover rover = createMarsRover(0, 0, Direction.N);
		moveRover(rover, "fffbbf");
		assertPosition(rover, 0, 2);
	}

	@Test
	public void turnRoverAroundLeft() {
		MarsRover rover = createMarsRover(0, 0, Direction.N);
		moveRover(rover, "l");
		assertDirection(rover, Direction.W);
		moveRover(rover, "l");
		assertDirection(rover, Direction.S);
		moveRover(rover, "l");
		assertDirection(rover, Direction.E);
		moveRover(rover, "l");
		assertDirection(rover, Direction.N);
	}

	@Test
	public void turnRoverAroundRight() {
		MarsRover rover = createMarsRover(0, 0, Direction.N);
		moveRover(rover, "r");
		assertDirection(rover, Direction.E);
		moveRover(rover, "r");
		assertDirection(rover, Direction.S);
		moveRover(rover, "r");
		assertDirection(rover, Direction.W);
		moveRover(rover, "r");
		assertDirection(rover, Direction.N);
	}

	@Test
	public void drivePathFromKataSpec() {
		MarsRover rover = createMarsRover(0, 0, Direction.N);
		moveRover(rover, "ffrff");
		assertDirection(rover, Direction.E);
		assertPosition(rover, 2, 2);
	}

	@Test
	public void driveRoverBackwardsAroundCorners() {
		MarsRover rover = createMarsRover(0, 0, Direction.S);
		moveRover(rover, "bbrbb");
		assertDirection(rover, Direction.W);
		assertPosition(rover, 2, 2);
	}

	@Test
	public void setRoverOnGrid() {
		MarsRover rover = createMarsRover(101, 100, Direction.N);
		rover.landOn(Planet.createWithDimensions(100, 100));
		assertPosition(rover, 1, 0);
	}

	@Test
	public void roverStaysOnGrid() {
		MarsRover rover = createMarsRover(0, 0, Direction.N);
		rover.landOn(Planet.createWithDimensions(100, 100));
		moveRover(rover, "b");
		assertPosition(rover, 0, 99);
		moveRover(rover, "f");
		assertPosition(rover, 0, 0);
	}

	@Test
	public void roverChecksObstaclesOnSettingPlanet() {
		MarsRover rover = createMarsRover(0, 0, Direction.N);
		Planet planet = Planet.createWithDimensions(100, 100);
		planet.addObstacle(new Position(0, 0));
		try {
			rover.landOn(planet);
		} catch (IllegalArgumentException e) {
			assertThat(e.getCause(),
					instanceOf(ObstacleEncounteredException.class));
		}
	}

	@Test
	public void roverThrowsExceptionAtObstacle() {
		MarsRover rover = createMarsRover(2, 0, Direction.N);
		Planet mars = spy(Planet.createWithDimensions(10, 10));
		Position obstacle = new Position(2, 2);
		mars.addObstacle(obstacle);
		rover.landOn(mars);
		try {
			rover.move("ffff".toCharArray());
			fail();
		} catch (ObstacleEncounteredException e) {
			assertPosition(rover, 2, 1);
		}

		verify(mars).obstacleAt(new Position(2, 0));
		verify(mars).wrap(new Position(2, 0));
		verify(mars).obstacleAt(new Position(2, 1));
		verify(mars).wrap(new Position(2, 1));
		verify(mars).obstacleAt(new Position(2, 2));

	}

	private void moveRover(MarsRover rover, String commandString) {
		try {
			rover.move(commandString.toCharArray());
		} catch (ObstacleEncounteredException e) {
		}
	}

	private void assertPosition(MarsRover rover, int x, int y) {
		assertThat(rover.x(), equalTo(x));
		assertThat(rover.y(), equalTo(y));
	}

	private void assertDirection(MarsRover rover, Direction direction) {
		assertThat(rover.direction(), equalTo(direction));
	}

	private MarsRover createMarsRover(int x, int y, Direction d) {
		return new MarsRover(x, y, d);
	}
}
