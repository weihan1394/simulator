package tehpeng.simulator.model;

public class Coordinate {
  int x;
  int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
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
