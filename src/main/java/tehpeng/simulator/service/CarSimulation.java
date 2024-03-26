package tehpeng.simulator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import tehpeng.simulator.model.Car;

public class CarSimulation {
  public HashMap<String, List<String>> moveStep(HashMap<String, Car> lsCarMap, int currCommand, int inputBoundaryX,
      int inputBoundaryY) {
    // move car for one step
    HashMap<String, List<String>> coordinateMap = new HashMap<>();
    for (String key : lsCarMap.keySet()) {
      Car car = lsCarMap.get(key);
      nextMove(car, currCommand, inputBoundaryX, inputBoundaryY);

      String coordString = Arrays.toString(car.getCurrCoordinate());
      if (!coordinateMap.containsKey(coordString)) {
        // new coordinate; add the coordinate and the index (car)
        List<String> indexList = new ArrayList<>();
        indexList.add(car.getName());
        coordinateMap.put(coordString, indexList);
      } else {
        // existing coordinate; add the index (car)
        List<String> indexList = coordinateMap.get(coordString);
        indexList.add(car.getName());
      }

      System.out.println(car.toString());
    }
    return coordinateMap;
  }

  private void nextMove(Car car, int index, int inputBoundaryX, int inputBoundaryY) {
    if ((car.getCollisionWith().size() == 0) && (car.getCurrCommand() < car.getCommands().size())) {
      // set current command
      car.setCurrCommand(index);
      // car not collided and car still have command
      char currCommand = car.getCommands().get(index);
      if (currCommand == 'F') {
        // move forward
        int currDirection = car.getCurrDirection();
        int currDirectionIndex = currDirection % 2;
        if (currDirection > 1) {
          // moving south or west (-1)
          if (currDirectionIndex == 0) {
            car.minusCurrCoordinateY();
          } else {
            car.minusCurrCoordinateX();
          }
        } else {
          // moving north or east (+1)
          if (currDirectionIndex == 0) {
            car.plusCurrCoordinateY(inputBoundaryY);
          } else {
            car.plusCurrCoordinateX(inputBoundaryX);
          }
        }
      } else if ((currCommand == 'R') || (currCommand == 'L')) {
        // move direction
        // TODO: move to another method
        int newDirection = 9;
        // +4 to handle -1
        if (currCommand == 'R') {
          newDirection = (car.getCurrDirection() + 1 + 4) % 4;
        } else if (currCommand == 'L') {
          newDirection = (car.getCurrDirection() - 1 + 4) % 4;
        }

        car.setCurrDirection(newDirection);
      }

      // check if the car still have any more command?
      if (car.getCurrCommand() == (car.getCommands().size() - 1)) {
        car.setCompleted();
      }
    }
  }
}
