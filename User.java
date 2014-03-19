package LightCycles;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class User {
	private PrintWriter out;
	private BufferedReader in;
	private Player player;

	public User() {}
	
	private void getPlayer(GamePanel gameManager) {
		player = gameManager.getPlayerOne();
	}
	
	private void getData() {
		//handleData(in.read());
		//or something
	}
	
	public void sendData(String data) {out.println(data);}
	
	private void handleData(String data) {}
}
