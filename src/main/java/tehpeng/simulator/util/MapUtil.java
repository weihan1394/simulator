package tehpeng.simulator.util;

import tehpeng.simulator.model.Car;

public class MapUtil {
  // based on x, y convert to coordinate
  public static Integer[] convertCoordinate(int x, int y, int maxX, int maxY) {
    Integer[] coordinate = new Integer[4];
    coordinate[0] = maxY - y; // number of steps to north
    coordinate[1] = maxX - x; // number of steps to east
    coordinate[2] = y; // number of steps to south
    coordinate[3] = x; // number of steps to west

    return coordinate;
  }

  public static Integer[] moveCoordinate(Integer[] Coordinate, int direction) {
    return null;
  }

  public static Integer convertDirectionToIndex(String input) {
    char direction = CommonUtil.convertStringToChar(input);
    if (direction == 'N') {
      return 0;
    } else if (direction == 'E') {
      return 1;
    } else if (direction == 'S') {
      return 2;
    } else if (direction == 'W') {
      return 3;
    }

    // 9 refers to error
    return 9;
  }

  public static Character convertIndexToDirection(int input) {
    if (input == 0) {
      return 'N';
    } else if (input == 1) {
      return 'E';
    } else if (input == 2) {
      return 'S';
    } else if (input == 3) {
      return 'W';
    }

    // 9 refers to error
    return 'X';
  }

  public static boolean checkCollision(Car car1, Car car2) {
    Integer[] car1Coordinate = car1.getCurrCoordinate();
    Integer[] car2Coordinate = car2.getCurrCoordinate();
    if ((car1Coordinate[0] == car2Coordinate[0]) && (car1Coordinate[1] == car2Coordinate[1])) {
      return true;
    }

    return false;
  }
}
