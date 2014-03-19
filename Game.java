package game;

import javax.swing.*;

import panels.GamePanel;
import panels.LobbyPanel;

import java.awt.*;

@SuppressWarnings("serial")
public class Game extends JFrame {
	private JMenuBar MenuBar;
	private JMenu disconnectMenu;
	private JPanel resizePanel;
	private GamePanel gamePanel;
	private LobbyPanel lobbyPanel;
	private boolean gameRunning;

	public Game() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setFocusable(true);
		setTitle("Light Bikes");
		setMinimumSize(new Dimension (900,600));
		setPreferredSize(new Dimension(1000, 800));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		initComponents();
	}

	public void changePlayerColor(Color c) {
		gamePanel.setColor(c);
	}

	private void initComponents() {
		resizePanel = new JPanel();
		gamePanel = new GamePanel();
		MenuBar = new JMenuBar();
		disconnectMenu = new JMenu();
		resizePanel.setBackground(Color.black);
		resizePanel.setLayout(new GridBagLayout());
		lobbyPanel = new LobbyPanel(this);
		gamePanel.add(lobbyPanel, new GridBagConstraints());
		resizePanel.add(gamePanel, new GridBagConstraints());
		getContentPane().add(resizePanel);

		disconnectMenu.setText("Disconnect");
		MenuBar.add(disconnectMenu);
		setJMenuBar(MenuBar);

		pack();
		setLocationRelativeTo(null);
	}

	public void removeLobby() {
		gamePanel.remove(lobbyPanel);
		gamePanel.revalidate();
		gamePanel.repaint();
		gameRunning = true;
		runGame();
	}

	private void runGame() {
		if(gameRunning) {gamePanel.GameManager(2);}
	}

	public static void systemLookAndFeel(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		systemLookAndFeel();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Game().setVisible(true);
			}
		});
	}
}
