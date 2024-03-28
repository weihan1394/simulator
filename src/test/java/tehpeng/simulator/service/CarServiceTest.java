package tehpeng.simulator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import tehpeng.simulator.model.Car;

public class CarServiceTest {
  @Test
  void givenCarHaveMoreCommands_whenhasNextStep_ReturnTrue() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    lsCarMap.put("car1", new Car("A", 0, 0, 0, "FFRFFFFRRL"));
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    boolean hasNextStep = carService.hasNextStep();

    // Assert
    assertTrue(hasNextStep);
  }

  @Test
  void givenCarHaveNoMoreCommands_whenhasNextStep_ReturnFalse() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 0, 0, 0, "FFRFFFFRRL");
    car.setCompleted();
    car.setCurrCoordinate(new Integer[] { 5, 4 });
    car.setCurrCommand(9);
    car.setCurrDirection(2);
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    boolean hasNextStep = carService.hasNextStep();

    // Assert
    assertFalse(hasNextStep);
  }

  @Test
  void givenTwoCarsCollide_whenvalidateCollision_CorrectlyHandledAndCarCompleted() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car carA = new Car("A", 0, 0, 0, "F");
    Car carB = new Car("B", 0, 0, 0, "F");
    lsCarMap.put(carA.getName(), carA);
    lsCarMap.put(carB.getName(), carB);
    CarService carService = new CarService(lsCarMap, 10, 10);

    // When
    carService.nextMove(); // Both cars move to the same coordinate

    // Assert
    assertTrue(carA.getCompleted()); // car A should be marked as completed
    assertTrue(carB.getCompleted()); // car B should be marked as completed
    assertTrue(carA.getCollideWith().contains(carB.getName())); // car A should have collided with car B
    assertTrue(carB.getCollideWith().contains(carA.getName())); // car B should have collided with car A
  }

  @Test
  void givenTwoCarsNoCollide_whenvalidateCollision_CorrectlyHandledAndCarNotCompleted() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car carA = new Car("A", 1, 1, 0, "FL");
    Car carB = new Car("B", 2, 2, 1, "FL");
    lsCarMap.put(carA.getName(), carA);
    lsCarMap.put(carB.getName(), carB);
    CarService carService = new CarService(lsCarMap, 10, 10);

    // When
    carService.nextMove();

    // Assert
    assertFalse(carA.getCompleted()); // car A should be marked as not completed
    assertFalse(carB.getCompleted()); // car B should be marked as not completed
    assertEquals(carA.getCollideWith().size(), 0); // car A no collision
    assertEquals(carB.getCollideWith().size(), 0); // car Bno collision
  }

  @Test
  void givenCarMoveForwardNorth_whennextMove_CorrectCoordinateUpdatedAndSameDirection() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 1, 2, 0, "FF");
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 10, 10);

    // When
    carService.nextMove(); // Move car forward

    // Assert
    assertEquals(3, car.getCurrCoordinate()[0]); // Coordinate y should increase by 1
    assertEquals(1, car.getCurrCoordinate()[1]); // Coordinate x should remain the same
    assertEquals(0, car.getCurrDirection()); // Direction should be same
    assertEquals(false, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void givenCarMoveForwardNorthExceedBoundary_whennextMove_CorrectCoordinateUpdatedAndSameDirection() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 1, 9, 0, "F");
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 10, 10);

    // When
    carService.nextMove(); // Move car forward

    // Assert
    assertEquals(9, car.getCurrCoordinate()[0]); // Coordinate y should increase by 1
    assertEquals(1, car.getCurrCoordinate()[1]); // Coordinate x should remain the same
    assertEquals(0, car.getCurrDirection());
    assertEquals(true, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void givenCarMoveForwardSouth_whennextMove_CorrectCoordinateUpdatedAndSameDirection() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 1, 2, 2, "F");
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 10, 10);

    // When
    carService.nextMove(); // Move car forward

    // Assert
    assertEquals(1, car.getCurrCoordinate()[1]); // Coordinate x should remain the same
    assertEquals(1, car.getCurrCoordinate()[0]); // Coordinate y should decrease by 1
    assertEquals(2, car.getCurrDirection()); // Direction should be same
    assertEquals(true, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void givenCarMoveForwardSouthExceedBoundary_whennextMove_CorrectCoordinateUpdatedAndSameDirection() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 1, 0, 2, "FF");
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 10, 10);

    // When
    carService.nextMove(); // Move car forward

    // Assert
    assertEquals(1, car.getCurrCoordinate()[1]); // Coordinate x should remain the same
    assertEquals(0, car.getCurrCoordinate()[0]); // Coordinate y should decrease by 1
    assertEquals(2, car.getCurrDirection()); // Direction should be same
    assertEquals(false, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void givenCarMoveForwardEast_whennextMove_CorrectCoordinateUpdatedAndSameDirection() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 1, 2, 1, "F");
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 10, 10);

    // When
    carService.nextMove(); // Move car forward

    // Assert
    assertEquals(2, car.getCurrCoordinate()[1]); // Coordinate x should increase by 1
    assertEquals(2, car.getCurrCoordinate()[0]); // Coordinate y should remain the same
    assertEquals(1, car.getCurrDirection()); // Direction should be same
    assertEquals(true, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void givenCarMoveForwardEastExceedBoundary_whennextMove_CorrectCoordinateUpdatedAndSameDirection() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 9, 2, 1, "FF");
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 10, 10);

    // When
    carService.nextMove(); // Move car forward

    // Assert
    assertEquals(9, car.getCurrCoordinate()[1]); // Coordinate x should increase by 1
    assertEquals(2, car.getCurrCoordinate()[0]); // Coordinate y should remain the same
    assertEquals(1, car.getCurrDirection()); // Direction should be same
    assertEquals(false, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void givenCarMoveForwardWest_whennextMove_CorrectCoordinateUpdatedAndSameDirection() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 1, 2, 3, "F");
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 10, 10);

    // When
    carService.nextMove(); // Move car forward

    // Assert
    assertEquals(2, car.getCurrCoordinate()[0]); // Coordinate y should remain the same
    assertEquals(0, car.getCurrCoordinate()[1]); // Coordinate x should decrease by 1
    assertEquals(3, car.getCurrDirection()); // Direction should be same
    assertEquals(true, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void givenCarMoveForwardWestExceeedBoundary_whennextMove_CorrectCoordinateUpdatedAndSameDirection() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 0, 2, 3, "FF");
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 10, 10);

    // When
    carService.nextMove(); // Move car forward

    // Assert
    assertEquals(2, car.getCurrCoordinate()[0]); // Coordinate y should remain the same
    assertEquals(0, car.getCurrCoordinate()[1]); // Coordinate x should decrease by 1
    assertEquals(3, car.getCurrDirection()); // Direction should be same
    assertEquals(false, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void givenCarMoveTurnRight_whennextMove_CorrectCoordinateUpdatedAndSameDirection() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 1, 2, 3, "R");
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 10, 10);

    // When
    carService.nextMove(); // Move car forward

    // Assert
    assertEquals(2, car.getCurrCoordinate()[0]); // Coordinate y should remain the same
    assertEquals(1, car.getCurrCoordinate()[1]); // Coordinate x should remain the same
    assertEquals(true, car.getCompleted()); // Car should be finish completed
    assertEquals(0, car.getCurrDirection()); // Direction should change from north (0) to east (1)
  }

  @Test
  void givenCarMoveTurnLeft_whennextMove_CorrectCoordinateUpdatedAndSameDirection() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("A", 1, 2, 0, "L");
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(); // Move car forward

    // Assert
    assertEquals(2, car.getCurrCoordinate()[0]); // Coordinate y should remain the same
    assertEquals(1, car.getCurrCoordinate()[1]); // Coordinate x should remain the same
    assertEquals(3, car.getCurrDirection()); // Direction should change from east (1) to north (0)
    assertEquals(true, car.getCompleted()); // Car should be completed
  }
}
