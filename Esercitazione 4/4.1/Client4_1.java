import java.io.*;
import java.net.*;
import java.util.Scanner;

class clientnuovo{
    public static void main(String argv[]) throws Exception{
        int a,b;
        long prod;
        
        Scanner inFromUser = new Scanner(System.in);

        Socket clientSocket = new Socket("127.0.0.1", 6789);

        DataOutputStream outToServer = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        DataInputStream inFromServer = new DataInputStream (new BufferedInputStream(clientSocket.getInputStream()));

        // DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        // DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());

        System.out.println("Enter the first number");

        a = inFromUser.nextInt();

        System.out.println("Enter the second number");

        b = inFromUser.nextInt();

        outToServer.writeInt(a);
        outToServer.writeInt(b);
        outToServer.flush();

        prod = inFromServer.readLong();

        System.out.println("The multiplication of " + a + " and " + b + " is: " + prod);
    }
}