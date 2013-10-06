package rover;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import rover.MarsRover.Direction;

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
		assertThat(rover.x(), equalTo(0));
		assertThat(rover.y(), equalTo(0));

		rover = createMarsRover(10, 15, Direction.N);
		assertThat(rover.x(), equalTo(10));
		assertThat(rover.y(), equalTo(15));
	}

	@Test
	public void roverHasStartingDirection() {
		MarsRover rover = createMarsRover(0, 0, Direction.N);
		assertThat(rover.direction(), equalTo(Direction.N));
	}

	private MarsRover createMarsRover(int x, int y, Direction d) {
		return new MarsRover(x, y, d);
	}
}
