package tehpeng.simulator.model.old;

public class Coordinate {
  int x;
  int y;
  char direction;

  public Coordinate(int x, int y, char direction) {
    this.x = x;
    this.y = y;
    this.direction = direction;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public char getDirection() {
    return direction;
  }

  public void forwardNorth() {
    y += 1;
  }

  public void forwardSouth() {
    y -= 1;
  }

  public void forwardWest() {
    x += 1;
  }

  public void forwardEast() {
    x -= 1;
  }
}
