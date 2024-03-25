package tehpeng.simulator.model.old;

import java.util.List;

import tehpeng.simulator.util.CommonUtil;

public class CarOld extends Coordinate {
  private String name;
  private int initDirection; // N => 0, E => 1, S=> 2, W => 3
  private Character[] initCoordinate; // N, E, S, W
  private List<Character> lsCommand;

  // to be reset after the new run
  private int currDirection;
  private Character[] currCoordinate; // N, E, S, W
  private int currCommand;
  private boolean collided;

  // Constructor
  public CarOld(String name, String x, String y, char initDirection, String command) {
    super(Integer.parseInt(x), Integer.parseInt(y), initDirection);
    this.name = name;
    this.initDirection = initDirection;
    this.lsCommand = CommonUtil.convertStringToListChar(command);
    this.currDirection = initDirection;
    this.currCommand = 0;
    this.collided = false;
  }

  // @Override
  // public String toString() {
  // StringBuilder sb = new StringBuilder();
  // sb.append("Car {");
  // sb.append("name: ").append(name).append(", ");
  // sb.append("initial direction: ").append(initDirection).append(", ");
  // sb.append("current direction: ").append(getCurrDirection()).append(", ");
  // sb.append("current command index: ").append(currCommand).append(", ");
  // sb.append("coordinates: (").append(getX()).append(",
  // ").append(getY()).append(")");
  // sb.append("}");
  // return sb.toString();
  // }
}
