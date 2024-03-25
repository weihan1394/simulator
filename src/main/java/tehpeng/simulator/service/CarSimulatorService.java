package tehpeng.simulator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tehpeng.simulator.model.Car;
import tehpeng.simulator.util.CommonUtil;

public class CarSimulatorService {
  public List<Car> cars;

  private int inputBoundaryX;
  private int inputBoundaryY;

  private int carsOnMap;

  public CarSimulatorService(List<Car> cars, int inputBoundaryX, int inputBoundaryY) {
    this.cars = cars;
    this.inputBoundaryX = inputBoundaryX;
    this.inputBoundaryY = inputBoundaryY;

    this.carsOnMap = cars.size();
  }

  public void findCarCollision() {
    HashMap<Integer[], List<Integer>> coordinateIndexMap = findCarMatchingPosition(cars);
    for (Map.Entry<Integer[], List<Integer>> entry : coordinateIndexMap.entrySet()) {
      Integer[] key = entry.getKey();
      List<Integer> value = entry.getValue();

      if (value.size() > 1) { // represents multiple car at the same coordinate
        for (int carIdx : value) {
          // collidedCars.add(cars.get(carIdx));
          // cars.remove(carIdx);
          if (cars.get(carIdx).collided) {
            continue;
          }

          cars.get(carIdx).collidedWith.addAll(value);
          cars.get(carIdx).collidedWith.remove(carIdx);

          cars.get(carIdx).collided = true;
        }
      }
    }
  }

  public HashMap<Integer[], List<Integer>> findCarMatchingPosition(List<Car> lsCar) {
    HashMap<Integer[], List<Integer>> coordinateMap = new HashMap<>();

    for (int index = 0; index < lsCar.size(); index++) {
      Car car = lsCar.get(index);
      Integer[] key = car.getCoordinate();
      if (!coordinateMap.containsKey(key)) {
        // new coordinate; add the coordinate and the index (car)
        List<Integer> indexList = new ArrayList<>();
        indexList.add(index);
        coordinateMap.put(key, indexList);
      } else {
        // existing coordinate; add the index (car)
        List<Integer> indexList = coordinateMap.get(key);
        indexList.add(index);
      }
    }

    return coordinateMap;
  }

  public boolean hasCarsOnMap() {
    return this.carsOnMap > 0;
  }

  public void moveNext() {
    for (Car car : this.cars) {
      char currCommand = car.nextCommand();
      if (car.collided) {
        carsOnMap--;
        continue;
      }
      if (currCommand == 0) {
        carsOnMap--;
        continue;
      }

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
        if (currCommand == 'R') {
          newDirection = (car.getCurrDirection() + 1) % 4;
        } else if (currCommand == 'L') {
          newDirection = (car.getCurrDirection() - 1) % 4;
        }

        car.setCurrDirection(newDirection);
      }
    }

    findCarCollision();
  }
}