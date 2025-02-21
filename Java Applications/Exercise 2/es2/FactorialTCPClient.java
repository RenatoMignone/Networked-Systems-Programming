package es2;

import java.net.*;
import java.io.IOException;

public class FactorialTCPClient{

    public static void main(String[] args) {
        try {
            FactorialTCPClient fc = new FactorialTCPClient("127.0.0.1", 8080); //instantiates an ojbect of this class, using in the constructor the IP address and the port number
            fc.start(); //calls the method 
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public FactorialTCPClient(String h, int port) throws IOException {
        clientSocket = new Socket(h, port); //this constructor gives a value to the local attribute socket
    }

    public void start() throws IOException {
        ProtocolHandler ph = new FactorialClientHandler(clientSocket); //instantiates an object of the FactorialClientHandler's class to handle the requests
        ph.handle();

    }
    private Socket clientSocket;

}
