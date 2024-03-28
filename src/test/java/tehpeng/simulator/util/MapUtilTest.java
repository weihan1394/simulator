package tehpeng.simulator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tehpeng.simulator.model.Car;

public class MapUtilTest {
  @Test
  void testConvertDirectionToIndex() {
    assertEquals(0, MapUtil.convertDirectionToIndex("N"));
    assertEquals(1, MapUtil.convertDirectionToIndex("E"));
    assertEquals(2, MapUtil.convertDirectionToIndex("S"));
    assertEquals(3, MapUtil.convertDirectionToIndex("W"));
    assertEquals(9, MapUtil.convertDirectionToIndex("X")); // Test for invalid input
  }

  @Test
  void testConvertIndexToDirection() {
    assertEquals('N', MapUtil.convertIndexToDirection(0));
    assertEquals('E', MapUtil.convertIndexToDirection(1));
    assertEquals('S', MapUtil.convertIndexToDirection(2));
    assertEquals('W', MapUtil.convertIndexToDirection(3));
    assertEquals('X', MapUtil.convertIndexToDirection(9)); // Test for invalid input
  }

  @Test
  void testCheckCollision() {
    Car car1 = new Car("A", 1, 2, 0, "FFRFFFFRRL");
    Car car2 = new Car("B", 1, 2, 0, "FFRFFFFRRL");
    assertTrue(MapUtil.checkCollision(car1, car2));

    Car car3 = new Car("c", 3, 3, 0, "FFRFFFFRRL");
    assertFalse(MapUtil.checkCollision(car1, car3));
  }
}
