package tehpeng.simulator.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonUtil {
  public static boolean isValidInteger(String value) {
    try {
      Integer.parseInt(value);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }

  public static boolean isValidDirection(String value) {
    List<Character> lsDirection = Arrays.asList('N', 'S', 'E', 'W');
    if (isValidCharacter(value)) {
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

  public static boolean isValidCharacter(String value) {
    if (value.trim().length() == 1) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isValidBoundary(int value, int max) {
    if (value < max) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isValidBoundarySize(String[] value) {
    if (value.length == 2) {
      String value1 = value[0];
      String value2 = value[1];
      // x and y must be integer
      if (isValidInteger(value1) && isValidInteger(value2)) {
        return true;
      }
    }

    return false;
  }

  public static boolean isValidNotEmptyString(String value) {
    if (!value.trim().isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isValidCommand(String value) {
    boolean containsCommandCharacter = value.matches("[FRL]+");

    if (containsCommandCharacter) {
      return true;
    }

    return false;
  }

  public static boolean isValidCarName(String value) {
    if (isValidNotEmptyString(value)) {
      return true;
    }

    return false;
  }

  public static List<Character> convertStringToListChar(String value) {
    List<Character> lsOutput = new ArrayList<>();
    for (char c : value.toCharArray()) {
      lsOutput.add(c);
    }

    return lsOutput;
  }

  public static Character convertStringToChar(String value) {
    return value.charAt(0);
  }
}