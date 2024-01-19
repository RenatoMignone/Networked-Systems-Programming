package es2;

import es2.*;
import java.net.*;
import java.io.IOException;

//questo server a diffenza del precedente gestisce la comunicazione dei client con differenti threads 
public class FactorialTCPServer1 {

    public static void main(String[] args) {
        try {
            FactorialTCPServer1 fs = new FactorialTCPServer1(6789); //instanzia un oggetto di questa classe, per poi andare ad usare i suoi metodi
            fs.start(); //calls the method of this class
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public FactorialTCPServer1 (int port) throws IOException {
        welcomeSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while(true) {
            Socket connectionSocket = welcomeSocket.accept(); //calls the accept to create a new socket to communicate with the clients that wants to communicate
            FactorialConcurrentServerHandler fc = new FactorialConcurrentServerHandler(connectionSocket); //instantiates an object of FactorialConcurrentServerHandler class 
            fc.handle(); //uses the method of this class to handle the request from the remote client
        }

    }

    private ServerSocket welcomeSocket;

}
