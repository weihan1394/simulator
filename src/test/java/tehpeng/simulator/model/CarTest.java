package tehpeng.simulator.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CarTest {
  @Test
  void testInitialization() {
    Car car = new Car("TestCar", 1, 2, 0, "FRL");
    assertEquals("TestCar", car.getName());
    assertArrayEquals(new Integer[] { 2, 1 }, car.getCoordinate());
    assertEquals(0, car.getDirection());
    assertEquals(List.of('F', 'R', 'L'), car.getCommands());
    assertArrayEquals(new Integer[] { 2, 1 }, car.getCurrCoordinate());
    assertEquals(0, car.getCurrDirection());
    assertEquals(0, car.getCurrCommand());
    assertTrue(car.getCollideWith().isEmpty());
    assertFalse(car.getCompleted());
  }

  @Test
  void testMovement() {
    Car car = new Car("TestCar", 1, 2, 0, "FRL");
    car.plusCurrCoordinateY(5); // maxY is 5
    assertEquals(3, car.getCurrCoordinate()[0]);
    car.minusCurrCoordinateY();
    assertEquals(2, car.getCurrCoordinate()[0]);
    car.plusCurrCoordinateX(5); // maxX is 5
    assertEquals(2, car.getCurrCoordinate()[1]);
    car.minusCurrCoordinateX();
    assertEquals(1, car.getCurrCoordinate()[1]);
  }

  @Test
  void testCompletion() {
    Car car = new Car("TestCar", 1, 2, 0, "FRL");
    assertFalse(car.getCompleted());
    car.setCompleted();
    assertTrue(car.getCompleted());
  }

  @Test
  void testCommandIndex() {
    Car car = new Car("TestCar", 1, 2, 0, "FRL");
    assertEquals(0, car.getCurrCommand());
    car.setCurrCommand(2);
    assertEquals(2, car.getCurrCommand());
  }

  @Test
  void testDirection() {
    Car car = new Car("TestCar", 1, 2, 0, "FRL");
    assertEquals(0, car.getDirection());
    car.setCurrDirection(2);
    assertEquals(2, car.getCurrDirection());
  }

  @Test
  void testCurrCoordinate() {
    Car car = new Car("TestCar", 1, 2, 0, "FRL");
    car.setCurrCoordinate(new Integer[] { 3, 4 });
    assertArrayEquals(new Integer[] { 3, 4 }, car.getCurrCoordinate());
  }

  @Test
  void testCollision() {
    Car car1 = new Car("TestCar1", 1, 2, 0, "FRL");
    Car car2 = new Car("TestCar2", 1, 2, 0, "FRL");
    assertFalse(car1.getCompleted());
    assertFalse(car2.getCompleted());
    car1.setCollideWith(List.of("TestCar2"));
    car2.setCollideWith(List.of("TestCar1"));
    assertEquals(List.of("TestCar2"), car1.getCollideWith());
    assertEquals(List.of("TestCar1"), car2.getCollideWith());
  }

  @Test
  void testToString() {
    Car car = new Car("TestCar", 1, 2, 0, "FRL");
    assertEquals(
        "Car{name='TestCar', coordinate=[2, 1], direction=0, commands=[F, R, L], currCoordinate=[2, 1], currDirection=0, currCommand=0, collideWith=[], completed=false}",
        car.toString());
  }
}
