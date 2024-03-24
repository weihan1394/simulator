package tehpeng.simulator.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommonUtilTest {
  @Test
  @DisplayName("Test Check Integer")
  public void testCheckInteger() {
    assertTrue(CommonUtil.isValidInteger("123"));
    assertFalse(CommonUtil.isValidInteger("abc"));
  }

  @Test
  @DisplayName("Test Check Direction")
  public void testCheckDirection() {
    assertTrue(CommonUtil.isValidDirection("N"));
    assertFalse(CommonUtil.isValidDirection("X"));
    assertFalse(CommonUtil.isValidDirection("AB"));
  }

  @Test
  @DisplayName("Test Check Character")
  public void testCheckCharacter() {
    assertTrue(CommonUtil.isValidCharacter("A"));
    assertFalse(CommonUtil.isValidCharacter("AB"));
  }

  @Test
  @DisplayName("Test Check Boundary")
  public void testCheckBoundary() {
    assertTrue(CommonUtil.isValidBoundary(5, 10));
    assertFalse(CommonUtil.isValidBoundary(15, 10));
  }
}
