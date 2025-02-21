import java.io.*;
import java.net.*;
import java.util.*;

class Client4_3{

    public static final int YOU = 0;
    public static final int PEER = 1;
    public static final int EXIT = 2;

    public static void main(String argv[]) throws Exception{

        int size;
        byte[] buffer = new byte[256];

        Scanner inFromUser = new Scanner(System.in);

        ServerSocket welcomeSocket =  new ServerSocket(6789);

        while(true){
            System.out.println("##################################################################");
            Socket connectionSocket = welcomeSocket.accept();
            char status = PEER;

            DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            while(status != EXIT){
            switch(status){
                case YOU:
                    do{
                        System.out.println("YOU>  ");
                        buffer = inFromUser.nextLine().getBytes();
                        outToClient.writeInt(buffer.length);
                        outToClient.write(buffer);
                        if(buffer[buffer.length-1] == '-') status = PEER;
                        else if(buffer[buffer.length-1]=='.') status = EXIT;
                    }while(status == YOU);
                    break;

                case PEER:  
                    do{
                        System.out.println("PEER> ");
                        buffer = new byte[256];
                        size = inFromClient.readInt();
                        inFromClient.read(buffer);
                        System.out.println(new String(buffer));
                        if(buffer[size-1] == '-') status = YOU;
                        else if(buffer[size-1] == '.') status = EXIT;
                    }while(status == PEER);
                    break;
            }
        }  
        System.out.println("Connessione terminata");
        connectionSocket.close();
        }
    }
}