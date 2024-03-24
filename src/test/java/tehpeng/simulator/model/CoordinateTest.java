package tehpeng.simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CoordinateTest {
  @Test
  public void testCoordinateConstructorAndGetters() {
    // Create a Coordinate object for testing
    Coordinate coordinate = new Coordinate(3, 5);

    // Test getX() method
    assertEquals(3, coordinate.getX());

    // Test getY() method
    assertEquals(5, coordinate.getY());
  }

  @Test
  public void testForwardNorth() {
    // Create a Coordinate object for testing
    Coordinate coordinate = new Coordinate(3, 5);

    // Move north
    coordinate.forwardNorth();

    // Test getY() method after moving north
    assertEquals(6, coordinate.getY());
  }

  @Test
  public void testForwardSouth() {
    // Create a Coordinate object for testing
    Coordinate coordinate = new Coordinate(3, 5);

    // Move south
    coordinate.forwardSouth();

    // Test getY() method after moving south
    assertEquals(4, coordinate.getY());
  }

  @Test
  public void testForwardWest() {
    // Create a Coordinate object for testing
    Coordinate coordinate = new Coordinate(3, 5);

    // Move west
    coordinate.forwardWest();

    // Test getX() method after moving west
    assertEquals(4, coordinate.getX());
  }

  @Test
  public void testForwardEast() {
    // Create a Coordinate object for testing
    Coordinate coordinate = new Coordinate(3, 5);

    // Move east
    coordinate.forwardEast();

    // Test getX() method after moving east
    assertEquals(2, coordinate.getX());
  }
}
