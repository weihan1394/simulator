package tehpeng.simulator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CommonUtilTest {
  @Test
  void testIsValidInteger() {
    assertTrue(CommonUtil.isValidInteger("123"));
    assertTrue(CommonUtil.isValidInteger("-456"));
    assertFalse(CommonUtil.isValidInteger("abc"));
    assertFalse(CommonUtil.isValidInteger("12.3"));
  }

  @Test
  void testIsValidDirection() {
    assertTrue(CommonUtil.isValidDirection("N"));
    assertTrue(CommonUtil.isValidDirection("S"));
    assertTrue(CommonUtil.isValidDirection("E"));
    assertTrue(CommonUtil.isValidDirection("W"));
    assertFalse(CommonUtil.isValidDirection("X"));
    assertFalse(CommonUtil.isValidDirection(""));
  }

  @Test
  void testIsValidCharacter() {
    assertTrue(CommonUtil.isValidCharacter("A"));
    assertTrue(CommonUtil.isValidCharacter("1"));
    assertFalse(CommonUtil.isValidCharacter(""));
    assertFalse(CommonUtil.isValidCharacter("AB"));
  }

  @Test
  void testIsValidBoundary() {
    assertTrue(CommonUtil.isValidBoundary(5, 10));
    assertFalse(CommonUtil.isValidBoundary(15, 10));
  }

  @Test
  void testIsValidBoundarySize() {
    assertTrue(CommonUtil.isValidBoundarySize(new String[] { "5", "10" }));
    assertFalse(CommonUtil.isValidBoundarySize(new String[] { "5" }));
    assertFalse(CommonUtil.isValidBoundarySize(new String[] { "5", "abc" }));
  }

  @Test
  void testIsValidNotEmptyString() {
    assertTrue(CommonUtil.isValidNotEmptyString("Hello"));
    assertFalse(CommonUtil.isValidNotEmptyString(""));
  }

  @Test
  void testIsValidCommand() {
    assertTrue(CommonUtil.isValidCommand("FRL"));
    assertTrue(CommonUtil.isValidCommand("FFF"));
    assertFalse(CommonUtil.isValidCommand("123"));
    assertFalse(CommonUtil.isValidCommand("ABC"));
    assertFalse(CommonUtil.isValidCommand(""));
  }

  @Test
  void testIsValidNoEmptySpaceString() {
    assertTrue(CommonUtil.isValidNoEmptySpaceString("Hello World"));
    assertFalse(CommonUtil.isValidNoEmptySpaceString("Hello  World"));
  }

  @Test
  void testIsValidCarName() {
    assertTrue(CommonUtil.isValidCarName("Car1"));
    assertTrue(CommonUtil.isValidCarName("Car2"));
    assertFalse(CommonUtil.isValidCarName(""));
  }

  @Test
  void testConvertStringToListChar() {
    List<Character> result = CommonUtil.convertStringToListChar("Hello");
    assertEquals(List.of('H', 'e', 'l', 'l', 'o'), result);
  }

  @Test
  void testConvertStringToChar() {
    Character result = CommonUtil.convertStringToChar("A");
    assertEquals('A', result);
  }
}
