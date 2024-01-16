package es2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;


//a simple server thats handles communications without using concurrent programming with threads
public class FactorialTCPServer {
    public static void main(String [] argv) {
        int n;
        long fact;
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            while(true) {
                Socket connectionSocket = serverSocket.accept();
                DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                n = inFromClient.readInt();

                fact = factorial(n); //calls the static function of this class

                outToClient.writeLong(fact);
            }
        }catch(IOException e) {
            System.err.println("SOCKET ERROR");
            e.printStackTrace();
        }
    }

    private static long factorial(int n) {
        if(n < 1) return 1;
        return n * factorial(n - 1);
    }
}
