package tehpeng.simulator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tehpeng.simulator.util.CommonUtil;

public class Car {
  // init data
  private String name;
  private Integer[] coordinate; // [south, west] [y, x]
  private int direction; // N:0 E:1 S:2 W:3
  private List<Character> commands;

  // moving
  private Integer[] currCoordinate; // [south, west] [y, x]
  private int currDirection; // N:0 E:1 S:2 W:3
  private int currCommand;
  // private Map<String, Integer> lsCollisionWith;
  private List<String> collisionWith;

  private boolean completed;

  public List<String> getCollisionWith() {
    return collisionWith;
  }

  public void setCollisionWith(List<String> collisionWith) {
    this.collisionWith = collisionWith;
  }

  // Constructor
  public Car(String name, int x, int y, int maxX, int maxY, int direction, String command) {
    this.name = name;
    this.coordinate = new Integer[2];
    coordinate[0] = y; // north remaining
    coordinate[1] = x; // south remaining
    this.direction = direction;
    this.commands = CommonUtil.convertStringToListChar(command);

    this.currCoordinate = new Integer[2];
    this.currCoordinate[0] = this.coordinate[0];
    this.currCoordinate[1] = this.coordinate[1];
    this.currDirection = direction;
    this.currCommand = 0;
    // this.lsCollisionWith = new HashMap<>();
    this.collisionWith = new ArrayList<>();
    this.completed = false;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted() {
    this.completed = true;
  }

  public void setCurrCommand(int currCommand) {
    this.currCommand = currCommand;
  }

  // public void setLsCollisionWith(Map<String, Integer> lsCollisionWith) {
  // this.lsCollisionWith = lsCollisionWith;
  // }

  // public Map<String, Integer> getCollisionWith() {
  // return lsCollisionWith;
  // }

  public void resetSimulation() {
    this.currCoordinate[0] = this.coordinate[0];
    this.currCoordinate[1] = this.coordinate[1];
    this.currDirection = this.direction;

    this.currCommand = 0;
  }

  public String getName() {
    return name;
  }

  public Integer[] getCoordinate() {
    return coordinate;
  }

  public int getDirection() {
    return direction;
  }

  public List<Character> getCommands() {
    return commands;
  }

  public Integer[] getCurrCoordinate() {
    return currCoordinate;
  }

  public void setCurrCoordinate(Integer[] newCurrCoordinate) {
    this.currCoordinate = newCurrCoordinate;
  }

  public int getCurrDirection() {
    return currDirection;
  }

  public void setCurrDirection(int newCurrDirection) {
    this.currDirection = newCurrDirection;
  }

  public void plusCurrCoordinateY(int maxY) {
    int currY = this.currCoordinate[0];
    if (currY < (maxY - 1)) {
      this.currCoordinate[0] = currY + 1;
    }
  }

  public void plusCurrCoordinateX(int maxX) {
    int currX = this.currCoordinate[1];
    if (currX < (maxX - 1)) {
      this.currCoordinate[1] = currX + 1;
    }
  }

  public void minusCurrCoordinateY() {
    int currY = this.currCoordinate[0];
    if (currY > 0) {
      this.currCoordinate[0] = currY - 1;
    }
  }

  public void minusCurrCoordinateX() {
    int currX = this.currCoordinate[1];
    if (currX > 0) {
      this.currCoordinate[1] = currX - 1;
    }
  }

  public int getCurrCommand() {
    return currCommand;
  }

  // public void addCollision(String carName) {
  // lsCollisionWith.put(carName, currCommand);
  // }

  // toString method
  @Override
  public String toString() {
    return "Car {" +
        "name='" + name + '\'' +
        ", coordinate=" + Arrays.toString(coordinate) +
        ", direction=" + direction +
        ", commands=" + commands +
        ", currCoordinate=" + Arrays.toString(currCoordinate) +
        ", currDirection=" + currDirection +
        ", currCommand=" + currCommand +
        ", lsCollisionWith=" + collisionWith +
        ", completed=" + completed +
        '}';
  }
}
