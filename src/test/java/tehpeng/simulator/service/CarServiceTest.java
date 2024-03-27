package tehpeng.simulator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import tehpeng.simulator.model.Car;

public class CarServiceTest {
  @Test
  void hasNextStep_CarNotCompleted_ReturnsTrue() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    lsCarMap.put("car1", new Car("car1", 0, 0, 5, 5, 0, "F"));
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    boolean hasNextStep = carService.hasNextStep();

    // Assert
    assertTrue(hasNextStep);
  }

  @Test
  void hasNextStep_CarCompleted_ReturnFalse() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("car1", 0, 0, 5, 5, 0, "F");
    car.setCompleted();
    lsCarMap.put(car.getName(), car);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    boolean hasNextStep = carService.hasNextStep();

    // Assert
    assertFalse(hasNextStep);
  }

  @Test
  void validateCollision_MultipleCarsCollideForward_CorrectlyHandled() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car1 = new Car("car1", 0, 0, 5, 5, 0, "F");
    Car car2 = new Car("car2", 0, 0, 5, 5, 0, "F");
    lsCarMap.put("car1", car1);
    lsCarMap.put("car2", car2);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Both cars move to the same coordinate

    // Assert
    assertTrue(car1.getCompleted()); // car1 should be marked as completed
    assertTrue(car2.getCompleted()); // car2 should be marked as completed
    assertTrue(car1.getCollideWith().contains("car2")); // car1 should have collided with car2
    assertTrue(car2.getCollideWith().contains("car1")); // car2 should have collided with car1
  }

  @Test
  void validateCollision_MultipleCarsCollideDirectionR_CorrectlyHandled() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car1 = new Car("car1", 0, 0, 5, 5, 0, "R");
    Car car2 = new Car("car2", 0, 0, 5, 5, 0, "R");
    lsCarMap.put("car1", car1);
    lsCarMap.put("car2", car2);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Both cars move to the same coordinate

    // Assert
    assertTrue(car1.getCompleted()); // car1 should be marked as completed
    assertTrue(car2.getCompleted()); // car2 should be marked as completed
    assertTrue(car1.getCollideWith().contains("car2")); // car1 should have collided with car2
    assertTrue(car2.getCollideWith().contains("car1")); // car2 should have collided with car1
  }

  @Test
  void validateCollision_MultipleCarsCollideDirectionL_CorrectlyHandled() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car1 = new Car("car1", 0, 0, 5, 5, 0, "L");
    Car car2 = new Car("car2", 0, 0, 5, 5, 0, "L");
    lsCarMap.put("car1", car1);
    lsCarMap.put("car2", car2);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Both cars move to the same coordinate

    // Assert
    assertTrue(car1.getCompleted()); // car1 should be marked as completed
    assertTrue(car2.getCompleted()); // car2 should be marked as completed
    assertTrue(car1.getCollideWith().contains("car2")); // car1 should have collided with car2
    assertTrue(car2.getCollideWith().contains("car1")); // car2 should have collided with car1
  }

  @Test
  void validateCollision_MultipleCarsNoCollision_CorrectlyHandled() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car1 = new Car("car1", 1, 1, 5, 5, 0, "FL");
    Car car2 = new Car("car2", 2, 2, 5, 5, 1, "FL");
    lsCarMap.put("car1", car1);
    lsCarMap.put("car2", car2);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Both cars move to the same coordinate

    // Assert
    assertFalse(car1.getCompleted()); // car1 should be marked as completed
    assertFalse(car2.getCompleted()); // car2 should be marked as completed
    assertEquals(car1.getCollideWith().size(), 0); // car1 no collision
    assertEquals(car2.getCollideWith().size(), 0); // car2 no collision
  }

  @Test
  void moveCarForward_MoveForwardNorth_CorrectCoordinatesUpdated() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("car1", 2, 2, 5, 5, 0, "F");
    lsCarMap.put("car1", car);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Move car forward

    // Assert
    assertEquals(3, car.getCurrCoordinate()[0]); // Coordinate y should increase by 1
    assertEquals(2, car.getCurrCoordinate()[1]); // Coordinate x should remain the same
    assertEquals(true, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void moveCarForward_MoveForwardSouth_CorrectCoordinatesUpdated() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("car1", 2, 2, 5, 5, 2, "F");
    lsCarMap.put("car1", car);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Move car forward

    // Assert
    assertEquals(1, car.getCurrCoordinate()[0]); // Coordinate y should decrease by 1
    assertEquals(2, car.getCurrCoordinate()[1]); // Coordinate x should remain the same
    assertEquals(true, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void moveCarForward_MoveForwardEast_CorrectCoordinatesUpdated() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("car1", 2, 2, 5, 5, 1, "F");
    lsCarMap.put("car1", car);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Move car forward

    // Assert
    assertEquals(2, car.getCurrCoordinate()[0]); // Coordinate y should remain the same
    assertEquals(3, car.getCurrCoordinate()[1]); // Coordinate x should increase by 1
    assertEquals(true, car.getCompleted()); // Car should be finish completed

  }

  @Test
  void moveCarForward_MoveForwardWest_CorrectCoordinatesUpdated() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("car1", 2, 2, 5, 5, 3, "F");
    lsCarMap.put("car1", car);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Move car forward

    // Assert
    assertEquals(2, car.getCurrCoordinate()[0]); // Coordinate y should remain the same
    assertEquals(1, car.getCurrCoordinate()[1]); // Coordinate x should decrease by 1
    assertEquals(true, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void moveCarForward_MoveRight_CorrectCoordinatesUpdated() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("car1", 2, 2, 5, 5, 3, "R");
    lsCarMap.put("car1", car);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Move car forward

    // Assert
    assertEquals(2, car.getCurrCoordinate()[0]); // Coordinate y should remain the same
    assertEquals(2, car.getCurrCoordinate()[1]); // Coordinate x should decrease by 1
    assertEquals(true, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void moveCarForward_MoveLeft_CorrectCoordinatesUpdated() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("car1", 2, 2, 5, 5, 3, "L");
    lsCarMap.put("car1", car);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Move car forward

    // Assert
    assertEquals(2, car.getCurrCoordinate()[0]); // Coordinate y should remain the same
    assertEquals(2, car.getCurrCoordinate()[1]); // Coordinate x should decrease by 1
    assertEquals(true, car.getCompleted()); // Car should be finish completed
  }

  @Test
  void moveCarDirection_TurnRight_CorrectDirectionUpdated() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("car1", 2, 2, 5, 5, 0, "R");
    lsCarMap.put("car1", car);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Turn car right

    // Assert
    assertEquals(1, car.getCurrDirection()); // Direction should change from north (0) to east (1)
  }

  @Test
  void moveCarDirection_TurnLeft_CorrectDirectionUpdated() {
    // Given
    HashMap<String, Car> lsCarMap = new HashMap<>();
    Car car = new Car("car1", 2, 2, 5, 5, 1, "L");
    lsCarMap.put("car1", car);
    CarService carService = new CarService(lsCarMap, 5, 5);

    // When
    carService.nextMove(0); // Turn car left

    // Assert
    assertEquals(0, car.getCurrDirection()); // Direction should change from east (1) to north (0)
  }
}
