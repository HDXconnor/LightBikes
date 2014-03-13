package LightCycles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class Game extends JFrame {

    private HashMap<String, Color> colorHash = new  HashMap<String, Color>();

    public Game() {
        purple = new Color(153,0,204);
        orange = new Color(255,153,0);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFocusable(true);
        setTitle("Light Bikes");
        setBackground(new Color(255, 255, 255));
        setMinimumSize(new Dimension (900,600));
        setPreferredSize(new Dimension(1000, 800));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

        initComponents();
        
        colorHash.put("Red", Color.RED);
        colorHash.put("Orange", orange);
        colorHash.put("Purple", purple);
        colorHash.put("Green", Color.green);
        colorHash.put("Blue", Color.blue);
        colorHash.put("Magenta", Color.magenta);
        colorHash.put("Yellow", Color.yellow);
        colorHash.put("White", Color.white);
    }

    private void buildLabels() {

    }
    private void initComponents() {

        resizePanel = new JPanel();
        gamePanel = new GamePanel();
        lobbyPanel = new LobbyPanel();
        chatPanel = new JPanel();
        chatScroll = new JScrollPane();
        chatBox = new JTextArea();
        chatInput = new JTextField();
        winPanel = new WinPanel();
        winScroll = new JScrollPane();
        winTable = new JTable();
        playerPanel = new JPanel();
        usernameInput = new JTextField();
        colorScroll = new JScrollPane();
        playerColorPanel = new JPanel();
        chatLabel = new JLabel();
        readyToggle = new JToggleButton();
        userLabel = new JLabel();
        MenuBar = new JMenuBar();
        disconnectMenu = new JMenu();

        buildTable();
        buildLabels();
        readyToggle();

        playerColorPanel.setBackground(Color.green);
        playerColorPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        resizePanel.setBackground(Color.DARK_GRAY);
        resizePanel.setLayout(new GridBagLayout());

        chatPanel.setPreferredSize(new Dimension(596, 150));

        chatBox.setColumns(20);
        chatBox.setRows(5);
        chatBox.setBorder(BorderFactory.createEtchedBorder());
        chatScroll.setViewportView(chatBox);

        chatInput.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        GroupLayout chatPanelLayout = new GroupLayout(chatPanel);
        chatPanel.setLayout(chatPanelLayout);
        chatPanelLayout.setHorizontalGroup(
                chatPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(chatPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(chatPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(chatScroll)
                                        .addGroup(chatPanelLayout.createSequentialGroup()
                                                .addComponent(chatInput, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 134, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        chatPanelLayout.setVerticalGroup(
                chatPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(chatPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chatScroll, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chatInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        lobbyPanel.add(chatPanel, BorderLayout.PAGE_END);



        winTable.setModel(new DefaultTableModel(
                new Object [][] {
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null}
                },
                new String [] {
                        "User", "Wins"
                }
        ) {
            @SuppressWarnings("rawtypes")
			Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false
            };

            @SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        winScroll.setViewportView(winTable);
        if (winTable.getColumnModel().getColumnCount() > 0) {
            winTable.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        GroupLayout winPanelLayout = new GroupLayout(winPanel);
        winPanel.setLayout(winPanelLayout);
        winPanelLayout.setHorizontalGroup(
                winPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, winPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(winScroll, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                .addContainerGap())
        );
        winPanelLayout.setVerticalGroup(
                winPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(winPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(winScroll, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
        );

        lobbyPanel.add(winPanel, BorderLayout.LINE_END);

        usernameInput.setText("Player 1");
        usernameInput.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));



        GroupLayout playerColorPanelLayout = new GroupLayout(playerColorPanel);
        playerColorPanel.setLayout(playerColorPanelLayout);
        playerColorPanelLayout.setHorizontalGroup(
                playerColorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );
        playerColorPanelLayout.setVerticalGroup(
                playerColorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );

        chatLabel.setText("Chat:");
        userLabel.setText("Username:");


        GroupLayout playerPanelLayout = new GroupLayout(playerPanel);
        playerPanel.setLayout(playerPanelLayout);
        playerPanelLayout.setHorizontalGroup(
                playerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(playerPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(playerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(playerPanelLayout.createSequentialGroup()
                                                .addComponent(chatLabel,50, 50, 50))
                                        .addGroup(GroupLayout.Alignment.TRAILING, playerPanelLayout.createSequentialGroup()
                                                .addComponent(playerColorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(playerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(playerPanelLayout.createSequentialGroup()
                                                .addComponent(userLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(usernameInput, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(playerPanelLayout.createSequentialGroup()
                                                .addGroup(playerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(playerPanelLayout.createSequentialGroup()
                                                                .addGap(116, 116, 116)
                                                                .addComponent(readyToggle, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, playerPanelLayout.createSequentialGroup()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(colorScroll, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(27, Short.MAX_VALUE))))
        );
        playerPanelLayout.setVerticalGroup(
                playerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(playerPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(playerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(usernameInput, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userLabel))
                                .addGap(18, 18, 18)
                                .addGroup(playerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(colorScroll, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(playerColorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(playerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(chatLabel, GroupLayout.Alignment.TRAILING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, playerPanelLayout.createSequentialGroup()
                                                .addComponent(readyToggle, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );

        lobbyPanel.add(playerPanel, BorderLayout.CENTER);
        gamePanel.add(lobbyPanel, new GridBagConstraints());
        resizePanel.add(gamePanel, new GridBagConstraints());
        getContentPane().add(resizePanel);

        disconnectMenu.setText("Disconnect");
        MenuBar.add(disconnectMenu);

        setJMenuBar(MenuBar);

        pack();
        setLocationRelativeTo(null);
    }
    
    private void readyToggle() {
    	readyToggle.setText("Ready!!");
        readyToggle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
		        addKeyListener(gamePanel);
		        removeLobby();
				runGame();
			}
        });
    }
    
    private void removeLobby() {
		gamePanel.remove(lobbyPanel);
		gamePanel.revalidate();
		gamePanel.repaint();
		gamePanel.repaint();
    }
    
    private void runGame() {
    	if(gameRunning) {gamePanel.GameManager(2);}
		gamePanel.isFocusable();
		gamePanel.repaint();
    }
    
    private void buildTable() {
        TableModel tm = new MyTableModel();
        final JTable table = new JTable(tm);
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn column = columnModel.getColumn(columnModel.getColumnCount() - 1);
        TableCellRenderer renderer = new MyTableCellRenderer();
        column.setCellRenderer(renderer);
        colorScroll.setViewportView(table);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {

                String tableColor = table.getValueAt(table.getSelectedRow(), 0).toString();
                if (tableColor != null) {
                    playerColorPanel.setBackground(colorHash.get(tableColor));
                    playerColorPanel.repaint();
                    gamePanel.setColor(colorHash.get(tableColor));
                }
            }
        });
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

    private JMenuBar MenuBar;

    private JTextArea chatBox;
    private JTextField chatInput;
    private JLabel chatLabel;
    private JPanel chatPanel;
    private JScrollPane chatScroll;
    private JScrollPane colorScroll;
    private JMenu disconnectMenu;
    private JPanel playerColorPanel;
    private JPanel playerPanel;
    private JToggleButton readyToggle;
    private JPanel resizePanel;
    private JLabel userLabel;
    private JTextField usernameInput;
    private JScrollPane winScroll;
    private JTable winTable;
    private boolean gameRunning = true;

    private GamePanel gamePanel;
    private WinPanel winPanel;
    private LobbyPanel lobbyPanel;
    private Color purple, orange;
}
