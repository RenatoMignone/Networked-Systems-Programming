package es1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Writer extends Thread{
	public Writer(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		String msg;
		try {
			Scanner inFromUser = new Scanner(System.in);
			PrintStream outToSocket = new PrintStream(this.socket.getOutputStream());
			while(true) {
				System.out.print("YOU> ");
				msg = inFromUser.nextLine();
				if(msg.charAt(msg.length() - 1) == '.') {
					inFromUser.close();
					throw new ConversationExeption();
				}
				outToSocket.println(msg);
			}
		} catch (IOException e) {
		} catch (ConversationExeption e) {
			System.err.println("Conversation closed");
			try { this.socket.close(); } catch (IOException e1) {}
		} 
		
		
		
	}

	private Socket socket;
}
