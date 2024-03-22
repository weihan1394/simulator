package tehpeng.simulator.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommonUtilTest {
  @Test
  @DisplayName("Test Check Integer")
  public void testCheckInteger() {
    assertTrue(CommonUtil.checkInteger("123"));
    assertFalse(CommonUtil.checkInteger("abc"));
  }

  @Test
  @DisplayName("Test Check Direction")
  public void testCheckDirection() {
    assertTrue(CommonUtil.checkDirection("N"));
    assertFalse(CommonUtil.checkDirection("X"));
    assertFalse(CommonUtil.checkDirection("AB"));
  }

  @Test
  @DisplayName("Test Check Character")
  public void testCheckCharacter() {
    assertTrue(CommonUtil.checkCharacter("A"));
    assertFalse(CommonUtil.checkCharacter("AB"));
  }

  @Test
  @DisplayName("Test Check Boundary")
  public void testCheckBoundary() {
    assertTrue(CommonUtil.checkBoundary(5, 10));
    assertFalse(CommonUtil.checkBoundary(15, 10));
  }
}
