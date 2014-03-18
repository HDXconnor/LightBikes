package LightCycles;

public class ScreenBuffer {
		  
	public final static int BOOST = -1, SHIELD = -2, PACMAN = -3, NUKE = -4, TRAIL = -5, WALL = -6;
	public final static int NUM_POWERS = 4;
	private int [][] screen;
	
	
	ScreenBuffer( int rows, int columns ) {
		screen = new int[rows][columns];  
		for( int i = 0; i < rows; i++ ) {
			screen[i][0] = WALL;
			screen[i][columns-1] = WALL;
		}
		for( int i = 0; i < columns; i++ ) {
			screen[0][i] = WALL;
			screen[rows-1][i] = WALL;
		}
	}
	
	public void makeTrailSegment(Point p) {
		setValueAt(p, TRAIL);
	}
	
	public boolean isTrailSegment(Point p) {
		if(valueAt(p) == TRAIL) {return true;}
		else{return false;}
	}

	public boolean isWall( Point p ) { 
		if(valueAt(p) == WALL){return true;}
		else{return false;}
	}
	
	public boolean isTrail(Point p) {
		if (valueAt(p) == TRAIL) {return true;}
		else{return false;}
	}
	
	public boolean isPowerUp(Point p) {
		if (valueAt(p) >= BOOST && valueAt(p) <= PACMAN) {return true;}
		else{return false;}
	}

	public boolean isOccupied( Point p ) { 
		if(valueAt(p) < 0){return true;}
		else{return false;}
	}

	public int valueAt( Point p ) { return screen[p.getRow()][p.getCol()]; } 
	
	public void setValueAt( Point p, int n ) {
		screen[p.getRow()][p.getCol()] = n;
	}

}