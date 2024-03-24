package tehpeng.simulator.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CarTest {
  @Test
  public void testCarConstructorAndGettersSetters() {
    // Create a Car object for testing
    Car car = new Car(3, 5, "MyCar", "N", new String[] { "command1", "command2" });

    // Test getters
    assertEquals(3, car.getX());
    assertEquals(5, car.getY());
    assertEquals("MyCar", car.getName());
    assertEquals("N", car.getInitDirection());
    assertArrayEquals(new String[] { "command1", "command2" }, car.getCommand());
    assertEquals("N", car.getCurrDirection());
    assertEquals(0, car.getCurrCommand());

    // Test setters
    car.setName("NewCar");
    car.setInitDirection("S");
    car.setCommand(new String[] { "command3", "command4" });
    car.setCurrDirection("W");
    car.setCurrCommand(2);

    // Test getters after setting new values
    assertEquals("NewCar", car.getName());
    assertEquals("S", car.getInitDirection());
    assertArrayEquals(new String[] { "command3", "command4" }, car.getCommand());
    assertEquals("W", car.getCurrDirection());
    assertEquals(2, car.getCurrCommand());
  }

  @Test
  public void testCarToString() {
    // Create a Car object for testing
    Car car = new Car(3, 5, "MyCar", "N", new String[] { "command1", "command2" });

    // Define the expected string representation of the car
    String expected = "Car {name: MyCar, initial direction: N, current direction: N, current command index: 0, coordinates: (3, 5)}";

    // Get the actual string representation by calling toString() method
    String actual = car.toString();

    // Assert that the actual string matches the expected string
    assertEquals(expected, actual);
  }
}
