package es2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.net.*;

public class FactorialClientHandler implements ProtocolHandler {

	public FactorialClientHandler(Socket socket) {
		this.socket = socket;
	}

	public void handle() {
		Scanner fromUser = null;
		try {
			fromUser = new Scanner(System.in);
			PrintStream toUser = new PrintStream(System.out);
			DataOutputStream outToServer = new DataOutputStream(this.socket.getOutputStream());
			DataInputStream inFromServer = new DataInputStream(this.socket.getInputStream());
			int n = 0;
			do {
				toUser.print("Insert an integer: ");
				n = fromUser.nextInt();
				outToServer.writeInt(n);
				long fact;
				fact = inFromServer.readLong();
				toUser.println("The factorial of " + n + " is " + fact);
			} while(n > 0);
		} catch(IOException e) {
			e.printStackTrace();
		}finally {
			if (socket != null) try { socket.close(); } catch (IOException e) {}
			if (fromUser != null) fromUser.close();
		}
	}

	private Socket socket;
}
