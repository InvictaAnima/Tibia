package tibia;

public class Point {
  private int row, column;

  public Point(int row,int column){
    this.row = row;
    this.column = column;    
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setColumn(int column) {
    this.column = column;
  }
  
  @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
  
  
}
