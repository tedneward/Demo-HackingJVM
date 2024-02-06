public class Point {
  private int x;
  private int y;
  
  public Point(int x, int y) {
    init(x, y);
  }
  
  private void init(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public String toString() {
    return "(" + x + "," + y + ")";
  }
}
