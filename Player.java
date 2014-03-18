package LightCycles;
import java.util.Random;
import java.util.ArrayList;


public class Player {
	public final static int  LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
	private Point powerUpLocation;
	private int powerUpValue;
	private ArrayList<Point> freePool;
	private BikeTrail trail;
	private ScreenBuffer image;
	private Random rand; 
	private int direction = RIGHT;
	private boolean isLeavingTrail = false;
	private int powers;

	public Player(int rows, int cols, ArrayList<Point> freePool, ScreenBuffer image, int playerNum) {
		powerUpLocation = null;
		trail = new BikeTrail();
		rand = new Random();
		this.freePool = freePool;
		this.image = image;
		int rowLimit = rows - 1, colLimit = cols - 1;
		powers = 0;

		for(int i = 1; i < rowLimit; i++) {
			for(int j = 1; j < colLimit; j++) {
				makeFree(new Point(i, j));
			}	
		}		
				
		Point initialPosition = new Point(3 * playerNum + 3, 3 * playerNum + 3);
		makeTrailSegment(initialPosition, true);

		powerUpValue = getAPowerUpValue();
		powerUpLocation = (Point)freePool.get(getAnIdxValue(freePool.size()));
		image.setValueAt(powerUpLocation, -powerUpValue);
	}

	public void setBoost() {powers = powers | Integer.parseInt("1000", 2);}
	public void setShield() {powers = powers | Integer.parseInt("0100", 2);}
	public void setPacman() {powers = powers | Integer.parseInt("0010", 2);}
	public void setNuke() {powers = powers | Integer.parseInt("0001", 2);}
	
	public boolean hasBoost() {return (powers & 1000) > 0;}
	public boolean hasShield() {return (powers & 0100) > 0;}
	public boolean hasPacman() {return (powers & 0010) > 0;}
	public boolean hasNuke() {return (powers & 0001) > 0;}

	public void removeBoost() {powers = powers & 0111;}																																						
	public void removeShield() {powers = powers & 1011;}
	public void removePacman() {powers = powers & 1101;}
	public void removeNuke() {powers = powers & 1110;}
	
	public void gainPower() {
		if (powerUpValue == 1) {setBoost();}
		else if (powerUpValue == 2) {setShield();}
		else if (powerUpValue == 3) {setPacman();}
		//else if (powerUpValue == 4) {setNuke();}
		printPowers();
	}
	
	public void setDirection(int direction) {this.direction = direction;}
	public int getDirection() {return direction;}
	public void toggleTrail() {isLeavingTrail = !isLeavingTrail; makeFree(trail.getHead().getPoint());}
	public void leaveTrail(boolean isLeavingTrail) {this.isLeavingTrail = isLeavingTrail;}
	
	
	public BikeTrail getBikeTrail() { return trail; }
	public int getPowerUpValue() { return powerUpValue; }
	public Point getPowerUpLocation() { return powerUpLocation; }
	public int getAPowerUpValue() {return Math.abs(rand.nextInt(ScreenBuffer.NUM_POWERS)) + 1;}
	public int getAnIdxValue(int max) {return Math.abs(rand.nextInt(max));}

	public boolean move() {
		TrailSegment head = trail.getHead();
		Point newPoint = null;
		if (direction == UP){newPoint = new Point(head.getRow() - 1,head.getCol());}
		else if (direction == DOWN){newPoint = new Point(head.getRow() + 1, head.getCol());}
		else if (direction == LEFT){newPoint = new Point(head.getRow(), head.getCol() - 1);}
		else if (direction == RIGHT){newPoint = new Point(head.getRow(), head.getCol() + 1);}
		
		return finalizeMove(newPoint, isLeavingTrail);
	}
  
	public boolean finalizeMove(Point p, boolean isLeavingTrail) {
		if(image.isOccupied(p)) {
			if (p.equals(powerUpLocation)) {
				consumePowerUp();
			} else {
				if (hasShield()) {
					TrailSegment toRemove = trail.getHead();
					makeFree(toRemove.getPoint());
					trail.hideHead();
					removeShield();
				} else {
					return false;
				}
			}
		}
		
		makeTrailSegment(p, isLeavingTrail);
		if (!isLeavingTrail) {freePool.add(p);}
		
		if (p.equals(powerUpLocation)){consumePowerUp();}

		return true;
	}

	private void consumePowerUp() {
		gainPower();
		powerUpValue = getAPowerUpValue();
		powerUpLocation = (Point)freePool.get(getAnIdxValue(freePool.size()));
		image.setValueAt(powerUpLocation, -powerUpValue);
	}
	
	private void makeFree(Point p) {
		freePool.add(p);
		image.setValueAt(p, endOfPool());
	}
  
	private int endOfPool() {
		return freePool.size() - 1;
	}
	
	private void makeTrailSegment(Point p, boolean isLeavingTrail) { 
		TrailSegment temp = new TrailSegment(p);
		trail.addToHead(temp, isLeavingTrail);
		image.setValueAt(freePool.get(endOfPool()), image.valueAt(p));
		freePool.set(endOfPool(), temp.getPoint());
		freePool.remove(endOfPool());
		image.setValueAt(p, ScreenBuffer.TRAIL);
    }
	
	private void printPowers() {
		if (hasBoost()) {System.out.println("boost");}
		if (hasShield()) {System.out.println("shield");}
		if (hasPacman()) {System.out.println("Pacman");}
		if (hasNuke()) {System.out.println("nuke");}
		System.out.println();
	}
}

