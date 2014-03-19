package game;

public class Powers {
	private int powerUpValue;
	private int powers;
	private Point powerUpLocation;
	
	public Powers() {
		powers = 0;
		powerUpLocation = null;
		
	}
	public void gainPower() {
		if (powerUpValue == 1) {setBoost();}
		else if (powerUpValue == 2) {setShield();}
		else if (powerUpValue == 3) {setPacman();}
		//else if (powerUpValue == 4) {setNuke();}
		printPowers();
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

	public int getPowerUpValue() { return powerUpValue; }
	public Point getPowerUpLocation() { return powerUpLocation; }
	
	public void setPowerLocation(Point point) {
		powerUpLocation = point;
	}
	
	private void printPowers() {
		if (hasBoost()) {System.out.println("boost");}
		if (hasShield()) {System.out.println("shield");}
		if (hasPacman()) {System.out.println("Pacman");}
		if (hasNuke()) {System.out.println("nuke");}
		System.out.println();
	}
}

