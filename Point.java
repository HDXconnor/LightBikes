package LightCycles;

public class Point {
	private int row, col;

	public Point( int x, int y ) {
		row = x;
		col = y;
	}

	public void setRow( int x ) { row = x; } 
	public int getRow() { return row; } 
	public void setCol( int x ) { col = x; } 
	public int getCol() { return col; } 
	
	
	public boolean equals( Point p ) {
		if (this.row == p.getRow() && this.col == p.getCol()){
			return true;
		} else {
			return false;
		}
	}
}