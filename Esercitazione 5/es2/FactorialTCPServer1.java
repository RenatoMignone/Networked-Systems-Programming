package es2;

import es2.*;
import java.net.*;
import java.io.IOException;

public class FactorialTCPServer1 {

    public static void main(String[] args) {
        try {
            FactorialTCPServer1 fs = new FactorialTCPServer1(6789);
            fs.start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public FactorialTCPServer1 (int port) throws IOException {
        welcomeSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            FactorialConcurrentServerHandler fc = new FactorialConcurrentServerHandler(connectionSocket);
            fc.handle();
        }

    }

    private ServerSocket welcomeSocket;

}
