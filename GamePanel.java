package LightCycles;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener, ActionListener{
	private int numRows, numCols, colWidth, rowHeight;
	private Timer timer;
	private Player[] players;
	private BikeTrail[] trails;
	private boolean[] playerAlive;
	private int numPlayers, numAlive;
	private Color playerColor;
	private User user;

	public GamePanel() {
		numRows = 60;
		numCols = 90;
		colWidth = rowHeight = 10;
		setBackground(Color.BLACK);
		setMinimumSize(new Dimension(900, 600));
		setPreferredSize(new Dimension(900, 600));
		setLayout(new GridBagLayout());
		//this.user = thisUser;
	}

	public void GameManager(int numPlayers) {
		this.numPlayers = numPlayers;
		if (numPlayers <= 1) return;
		setupPlayers();
		setFocusable(true);
		timer = new Timer(70, this);
		timer.start();
		addKeyListener(this);
	}

	@Override
	protected void paintComponent(Graphics gamePanel) {
		super.paintComponent(gamePanel);
		paintGame(gamePanel);
	}

	public void setupPlayers() {
		ArrayList<Point> freePool = new ArrayList<Point>();
		ScreenBuffer image = new ScreenBuffer(numRows, numCols);
		players = new Player[numPlayers];
		trails = new BikeTrail[numPlayers];
		playerAlive = new boolean[numPlayers];

		for (int i = 0; i < numPlayers; i++) {
			Player newPlayer = new Player(numRows, numCols, freePool, image, i);
			players[i] = newPlayer;
			trails[i] = newPlayer.getBikeTrail();
			playerAlive[i] = true;
		}
	}
	
	public Player getPlayerOne() {return players[0];}
	
	private void sendPosData(int direction) {user.sendData(Integer.toString(direction) + players[0].getPosition().toString());}
	private void sendTrailUpdate(boolean isLeavingTrail) {user.sendData(Boolean.toString(isLeavingTrail));}
	private void sendPlayerDeath(int playerNum) {user.sendData("Dead:" + playerNum);}

	public void keyPressed(KeyEvent ke) {
		if(! playerAlive[0]) return;
		int kc = ke.getKeyCode();
		int direction = players[0].getDirection();
		int direction2 = players[1].getDirection();

		if(kc == KeyEvent.VK_UP && direction != Player.DOWN) { 
			players[0].setDirection(Player.UP);
			//sendPosData(Player.UP);
		} else if(kc == KeyEvent.VK_DOWN  && direction != Player.UP) {
			players[0].setDirection(Player.DOWN);
			//sendPosData(Player.DOWN);
		} else if(kc == KeyEvent.VK_LEFT && direction != Player.RIGHT) {
			players[0].setDirection(Player.LEFT);
			//sendPosData(Player.LEFT);
		} else if(kc == KeyEvent.VK_RIGHT && direction != Player.LEFT) {
			players[0].setDirection(Player.RIGHT);
			//sendPosData(Player.RIGHT);
		} else if(kc == KeyEvent.VK_SPACE) {
			players[0].toggleTrail();
			//sendTrailUpdate(players[0].isLeavingTrail());
		} 

		/*
		 *  For demonstration purposes only
		 */
		else if(kc == KeyEvent.VK_W && direction2 != Player.DOWN) {players[1].setDirection(Player.UP);} 
		else if(kc == KeyEvent.VK_S && direction2 != Player.UP) {players[1].setDirection(Player.DOWN);} 
		else if(kc == KeyEvent.VK_D && direction2 != Player.LEFT) {players[1].setDirection(Player.RIGHT);} 
		else if(kc == KeyEvent.VK_A && direction2 != Player.RIGHT) {players[1].setDirection(Player.LEFT);} 
		else if(kc == KeyEvent.VK_SHIFT) {players[1].toggleTrail();}
		// Ignore above

	}

	public void keyReleased(KeyEvent ke) {}
	public void keyTyped(KeyEvent ke) {}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == timer) {
			movePlayers();
			checkTimer();
			repaint();
		}
	}

	public void movePlayers() {
		numAlive = 0;
		for (int i = 0; i < numPlayers; i++) {
			boolean isAlive = players[i].move();
			if (isAlive) {numAlive++;}
			else {
				playerAlive[i] = false;
				//sendPlayerDeath(i);
			}
		}
	}

	public void checkTimer() {if (numAlive <= 1 && timer.isRunning()) {timer.stop();}}

	public void paintGame(Graphics g) {
		if(numAlive <= 1) {gameOver(g); return;}

		drawBorders(g);
		drawPlayers(g);
		drawPowerUps(g);
	}

	public void gameOver(Graphics g) {g.drawString("Game over" , (900 / 2) - 45, 600 / 2 - 25);}

	public void drawPowerUps(Graphics g) {
		Color prev_color = g.getColor();
		Point p = players[0].getPowerUpLocation();
		g.setColor(Color.white);
		g.drawString("◎", p.getCol()*colWidth, (p.getRow() + 1) *rowHeight);
		g.setColor(prev_color);
	}

	public void drawPlayers(Graphics g) {
		for (int i = 0; i < numPlayers; i++) {
			if (playerAlive[i]) {
				BikeTrail curTrail = trails[i];
				curTrail.start();
				while(curTrail.moreElements()){
					TrailSegment temp = curTrail.nextElement();
					temp.display(g, colWidth * temp.getCol(), rowHeight * temp.getRow(), colWidth, rowHeight);
				}
			}
		}
	}

	public void setColor(Color tableColor){
		playerColor = tableColor;
	}

	public void drawBorders(Graphics g) {

		g.setColor(Color.blue);
		g.fillRect(0, 0, numCols * colWidth, rowHeight);
		g.fillRect(0, (numRows - 1) * rowHeight, numCols * colWidth, rowHeight);
		g.fillRect(0, 0, colWidth,  numRows * rowHeight);
		g.fillRect(colWidth * (numCols - 1), 0, colWidth, numRows * rowHeight);
		g.setColor(Color.green); //initial color with no table choice
		g.setColor(playerColor);
	}
	/*
	public static void main(String[] args) {
		new GamePanel().setVisible(true);
	}
	*/
}
