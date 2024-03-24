package tehpeng.simulator.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
    String value1 = value[0];
    String value2 = value[1];
    // x and y must be integer
    if ((value.length == 2) && (isValidInteger(value1) && (isValidInteger(value2)))) {
      return true;
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

  public static boolean isValidNoEmptySpaceString(String value) {

    Pattern pattern = Pattern.compile("\\s{2,}"); // Regular expression to match 2 or more consecutive whitespace
    Matcher matcher = pattern.matcher(value);

    if (matcher.find()) {
      return false;
    } else {
      return true;
    }
  }

  public static boolean isValidCarName(String value) {
    if (isValidNotEmptyString(value)) {
      return true;
    }

    return false;
  }
}