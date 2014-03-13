package LightCycles;
import java.awt.Graphics;

public class TrailSegment {
	private Point p;
	private TrailSegment next;
	private boolean isVisible;

	public TrailSegment( Point pp ) { 
		p = pp;
		next = null;
		isVisible = true;
	}

	public TrailSegment( int row, int col ) {
		p = new Point( row, col );
		next = null;
	}
	
	public void hide() {isVisible = false;}

	public Point getPoint() { return p; }
	
	public void setNext( TrailSegment ws ) { next = ws; }
	public TrailSegment getNext() { return next; }
	
	public int getRow() {return p.getRow();}
	public int getCol() {return p.getCol();}
	
	public void display( Graphics g, int x, int y, int width, int height ) {
		if (isVisible) {g.fillRect( x, y, width, height );}
	}
}