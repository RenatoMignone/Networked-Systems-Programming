package es1;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Reader extends Thread{

	public Reader(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		String msg;
		try {
			Scanner inFromSocket = new Scanner(this.socket.getInputStream());
			while(true) {
				msg = inFromSocket.nextLine();
				if(msg.charAt(msg.length() - 1) == '.') {
					inFromSocket.close();
					throw new ConversationExeption();
				}
				System.out.println("PEER> " + msg);
			}
		} catch (IOException e) {
		} catch (ConversationExeption e) {
			System.err.println("Conversation closed");
			try { this.socket.close(); } catch (IOException e1) {}
		} 
		
		
		
	}

	private Socket socket;
}
