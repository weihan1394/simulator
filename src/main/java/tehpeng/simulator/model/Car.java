package tehpeng.simulator.model;

public class Car extends Coordinate {
  private String name;
  private String initDirection;
  private String[] command;

  private String currDirection;
  private int currCommand;

  // Constructor
  public Car(int x, int y, String name, String initDirection, String[] command) {
    super(x, y);
    this.name = name;
    this.initDirection = initDirection;
    this.command = command;
    this.currDirection = initDirection;
    this.currCommand = 0;
  }

  // Getters and Setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInitDirection() {
    return initDirection;
  }

  public void setInitDirection(String initDirection) {
    this.initDirection = initDirection;
  }

  public String[] getCommand() {
    return command;
  }

  public void setCommand(String[] command) {
    this.command = command;
  }

  public String getCurrDirection() {
    return currDirection;
  }

  public void setCurrDirection(String currDirection) {
    this.currDirection = currDirection;
  }

  public int getCurrCommand() {
    return currCommand;
  }

  public void setCurrCommand(int currCommand) {
    this.currCommand = currCommand;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Car {");
    sb.append("name: ").append(name).append(", ");
    sb.append("initial direction: ").append(initDirection).append(", ");
    sb.append("current direction: ").append(getCurrDirection()).append(", ");
    sb.append("current command index: ").append(currCommand).append(", ");
    sb.append("coordinates: (").append(getX()).append(", ").append(getY()).append(")");
    sb.append("}");
    return sb.toString();
  }
}
