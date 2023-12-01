import java.io.*;
import java.net.*;
import java.util.Scanner;

class servernuovo{
    public static void main(String argv[])throws Exception{
        int a,b;
        long prod;

        ServerSocket welcomeSocket =  new ServerSocket(6789);

        while(true){
            Socket connectionSocket = welcomeSocket.accept();

            DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            a = inFromClient.readInt();

            b = inFromClient.readInt();

            prod = a * (long) b;

            outToClient.writeLong(prod);        

            connectionSocket.close();
        }
    }
}