package es2;

import java.net.*;
import java.io.IOException;

public class FactorialTCPClient{

    public static void main(String[] args) {
        try {
            FactorialTCPClient fc = new FactorialTCPClient("127.0.0.1", 8080);
            fc.start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public FactorialTCPClient(String h, int port) throws IOException {
        clientSocket = new Socket(h, port);
    }

    public void start() throws IOException {
        ProtocolHandler ph = new FactorialClientHandler(clientSocket);
        ph.handle();

    }
    private Socket clientSocket;

}
