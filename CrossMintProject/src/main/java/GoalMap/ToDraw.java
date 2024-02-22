package GoalMap;

public class ToDraw {

  private int row;
  private int col;
  private String shape;

  public ToDraw(int row, int col, String shape) {
    this.row = row;
    this.col = col;
    this.shape = shape;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public String getShape() {
    return shape;
  }
}
