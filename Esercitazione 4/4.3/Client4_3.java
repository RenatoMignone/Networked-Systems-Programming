import java.io.*;
import java.net.*;
import java.util.*;

class Client4_3{

    public static final int YOU = 0;
    public static final int PEER = 1;
    public static final int EXIT = 2;

    public static void main(String argv[]) throws Exception{

        char status = YOU;
        int size;
        byte[] buffer = new byte[256];

        Scanner inFromUser = new Scanner(System.in);

        Socket clientSocket = new Socket("127.0.0.1", 6789); //creiamo la socket di rete

        //DataOutputStream e DataInputStream sono dei decoratori, che implementano il pattern decorator
        //che 
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());

        while(status != EXIT){
            switch(status){
                case YOU:
                    do{
                        System.out.println("YOU>");
                        buffer = inFromUser.nextLine().getBytes();
                        outToServer.writeInt(buffer.length);
                        outToServer.write(buffer);
                        if(buffer[buffer.length-1] == '-') status = PEER;
                        else if(buffer[buffer.length-1]=='.') status = EXIT;
                    }while(status == YOU);
                    break;

                case PEER:  
                    do{
                        buffer = new byte[256];
                        size = inFromServer.readInt();
                        inFromServer.read(buffer);
                        System.out.println("PEER> " + new String(buffer));
                        if(buffer[size-1] == '-') status = YOU;
                        else if(buffer[size-1] == '.') status = EXIT;
                    }while(status == PEER);
                    break;
            }
        }
        System.out.println("Connessione terminata");
        inFromUser.close();
        clientSocket.close(); 
    }
}