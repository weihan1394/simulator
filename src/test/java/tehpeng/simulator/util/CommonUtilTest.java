package tehpeng.simulator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CommonUtilTest {
  @Test
  public void testIsValidInteger() {
    assertTrue(CommonUtil.isValidInteger("123"));
    assertTrue(CommonUtil.isValidInteger("-456"));
    assertFalse(CommonUtil.isValidInteger("abc"));
    assertFalse(CommonUtil.isValidInteger("12.3"));
  }

  @Test
  public void testIsValidDirection() {
    assertTrue(CommonUtil.isValidDirection("N"));
    assertTrue(CommonUtil.isValidDirection("S"));
    assertTrue(CommonUtil.isValidDirection("E"));
    assertTrue(CommonUtil.isValidDirection("W"));
    assertFalse(CommonUtil.isValidDirection("X"));
    assertFalse(CommonUtil.isValidDirection(""));
  }

  @Test
  public void testIsValidCharacter() {
    assertTrue(CommonUtil.isValidCharacter("A"));
    assertTrue(CommonUtil.isValidCharacter("1"));
    assertFalse(CommonUtil.isValidCharacter(""));
    assertFalse(CommonUtil.isValidCharacter("AB"));
  }

  @Test
  public void testIsValidBoundary() {
    assertTrue(CommonUtil.isValidBoundary(1, 10));
    assertFalse(CommonUtil.isValidBoundary(15, 10));
  }

  @Test
  public void testIsValidBoundarySize() {
    assertTrue(CommonUtil.isValidBoundarySize(new String[] { "10", "10" }));
    assertFalse(CommonUtil.isValidBoundarySize(new String[] { "10" })); // need to have x and y
    assertFalse(CommonUtil.isValidBoundarySize(new String[] { "5", "abc" }));
    assertFalse(CommonUtil.isValidBoundarySize(new String[] { "abc", "5" }));
  }

  @Test
  public void testIsValidNotEmptyString() {
    assertTrue(CommonUtil.isValidNotEmptyString("A"));
    assertFalse(CommonUtil.isValidNotEmptyString(""));
  }

  @Test
  public void testIsValidCommand() {
    assertTrue(CommonUtil.isValidCommand("FFRFFFFRRL"));
    assertTrue(CommonUtil.isValidCommand("FFLFFFFFFF"));
    assertFalse(CommonUtil.isValidCommand("123"));
    assertFalse(CommonUtil.isValidCommand("ABC"));
    assertFalse(CommonUtil.isValidCommand(""));
  }

  @Test
  public void testIsValidCarName() {
    assertTrue(CommonUtil.isValidCarName("A"));
    assertTrue(CommonUtil.isValidCarName("Car A"));
    assertFalse(CommonUtil.isValidCarName(""));
  }

  @Test
  public void testConvertStringToListChar() {
    List<Character> result = CommonUtil.convertStringToListChar("FFRFFFFRRL");
    assertEquals(List.of('F', 'F', 'R', 'F', 'F', 'F', 'F', 'R', 'R', 'L'), result);
  }

  @Test
  public void testConvertStringToChar() {
    Character result = CommonUtil.convertStringToChar("F");
    assertEquals('F', result);
  }
}
