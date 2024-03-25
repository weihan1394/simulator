package tehpeng.simulator.service;

import tehpeng.simulator.model.old.Coordinate;

public class SimulationService {
  public void command(char instruction, int x, int y) {

  }

  private char newDirection(char currentDirection, char direction) {
    switch (direction) {
      case 'N':
        // north
        if (direction == 'R') {
          return 'W';
        } else if (currentDirection == 'L') {
          return 'E';
        }
        break;
      case 'W':
        // west
        if (direction == 'R') {
          return 'S';
        } else if (currentDirection == 'L') {
          return 'N';
        }
        break;
      case 'S':
        // south
        if (direction == 'R') {
          return 'E';
        } else if (currentDirection == 'L') {
          return 'W';
        }
        break;
      case 'E':
        // east
        if (direction == 'R') {
          return 'N';
        } else if (currentDirection == 'L') {
          return 'S';
        }
        break;
      default:
        return 'X';
    }

    return 'X';
  }

  private Coordinate newMove(char direction, Coordinate coordinate) {
    switch (direction) {
      case 'N':
        // north (y + 1)
        coordinate.forwardNorth();
        break;
      case 'W':
        // west (x + 1)
        coordinate.forwardWest();
        break;
      case 'S':
        // south (y - 1)
        coordinate.forwardSouth();
        break;
      case 'E':
        // east (x - 1)
        coordinate.forwardEast();
        break;
      default:
        return null;
    }

    return null;
  }
}
