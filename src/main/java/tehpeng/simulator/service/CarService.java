package tehpeng.simulator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import tehpeng.simulator.model.Car;

public class CarService {

  private HashMap<String, Car> lsCarMap;
  private int inputBoundaryX;
  private int inputBoundaryY;
  private int currCommandIndex;

  public CarService(HashMap<String, Car> lsCarMap, int inputBoundaryX, int inputBoundaryY) {
    this.lsCarMap = lsCarMap;
    this.inputBoundaryX = inputBoundaryX;
    this.inputBoundaryY = inputBoundaryY;
  }

  private void validateCollision(HashMap<String, List<String>> coordinateMap) {
    // coordinateMap to see if there is any coordinate that have more than one car
    for (String key : coordinateMap.keySet()) {
      List<String> lsCarCollided = coordinateMap.get(key);

      // more than one car collided
      if (lsCarCollided.size() > 1) {
        // check if the car already collided before
        // yes: ignore
        // no: set collided = true, remove ownself

        for (String carCollidedName : lsCarCollided) {
          Car currCarCollided = lsCarMap.get(carCollidedName);

          if (currCarCollided.getCollideWith().size() == 0) {
            // initalize a new list
            List<String> lsCurrCarCollided = new ArrayList<>();
            lsCurrCarCollided.addAll(lsCarCollided);
            lsCurrCarCollided.remove(currCarCollided.getName()); // remove current car
            currCarCollided.setCollideWith(lsCurrCarCollided);

            // collided; means the car wont move anymore
            currCarCollided.setCompleted();
          }
        }
      }
    }
  }

  public boolean hasNextStep() {
    // check if all the cars collided or command ended
    for (String key : lsCarMap.keySet()) {
      Car car = lsCarMap.get(key);
      if (!car.getCompleted()) {
        return true;
      }
    }

    return false;
  }

  public void nextMove() {
    HashMap<String, List<String>> coordinateMap = new HashMap<>();

    for (String key : lsCarMap.keySet()) {
      Car car = lsCarMap.get(key);
      // car not collided, car still have command, car not yet completed
      if ((car.getCollideWith().size() == 0) && (car.getCurrCommand() < car.getCommands().size())
          && (car.getCompleted() == false)) {
        // set current command
        car.setCurrCommand(this.currCommandIndex);
        // car not collided and car still have command
        char currCommand = car.getCommands().get(this.currCommandIndex);
        if (currCommand == 'F') {
          moveCarForward(car);
        } else if ((currCommand == 'R') || (currCommand == 'L')) {
          moveCarDirection(car, currCommand);
        }

        // check if the car still have any more command?
        if (car.getCurrCommand() == (car.getCommands().size() - 1)) {
          car.setCompleted();
        }
      }

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
    }

    validateCollision(coordinateMap);
    currCommandIndex++;
  }

  private void moveCarForward(Car car) {
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
  }

  private void moveCarDirection(Car car, char currCommand) {
    // move direction
    int newDirection = 0;
    // +4 to handle -1
    if (currCommand == 'R') {
      newDirection = moveRight(car.getCurrDirection());
    } else if (currCommand == 'L') {
      newDirection = moveLeft(car.getCurrDirection());
    }

    car.setCurrDirection(newDirection);
  }

  private int moveLeft(int currDirection) {
    return (currDirection - 1 + 4) % 4;
  }

  private int moveRight(int currDirection) {
    return (currDirection + 1 + 4) % 4;
  }
}
