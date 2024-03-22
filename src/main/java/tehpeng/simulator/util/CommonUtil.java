package tehpeng.simulator.util;

import java.util.Arrays;
import java.util.List;

public class CommonUtil {
  public static boolean checkInteger(String value) {
    try {
      Integer.parseInt(value);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }

  public static boolean checkDirection(String value) {
    List<Character> lsDirection = Arrays.asList('N', 'S', 'E', 'W');
    if (checkCharacter(value)) {
      // check if value exist in direction
      if (lsDirection.contains(value.trim().charAt(0))) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public static boolean checkCharacter(String value) {
    if (value.trim().length() == 1) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean checkBoundary(int value, int max) {
    if (value < max) {
      return true;
    } else {
      return false;
    }
  }
}