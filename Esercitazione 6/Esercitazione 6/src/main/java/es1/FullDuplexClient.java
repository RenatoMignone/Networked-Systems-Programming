package es1;

import java.io.IOException;
import java.net.Socket;

public class FullDuplexClient {
	
	public FullDuplexClient(String ipAddr,int port) throws IOException {
		this.clientSocket = new Socket(ipAddr, port);
	}
	
	public void start() throws IOException{
		ProtocolHandler ph = new FullDuplexCommunicationHandler(this.clientSocket);
		ph.handle();
	}
	
	public static void main(String[] args) {
		try {
			FullDuplexClient fdc = new FullDuplexClient("127.0.0.1", 8080);
			fdc.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	Socket clientSocket;
}