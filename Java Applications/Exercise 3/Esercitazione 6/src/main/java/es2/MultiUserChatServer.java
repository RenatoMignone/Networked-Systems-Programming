package es2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiUserChatServer {
    
    public static void main(String[] args) {
        
        try{
            MultiUserChatServer mucs = new MultiUserChatServer(32500);
            mucs.go();
        }catch(Exception e){
            System.err.println("Errore");
        }

    }

    public MultiUserChatServer(int port) throws Exception{
        welcomeSocket = new ServerSocket(port);
    }

    public void go() throws IOException{

        Room room = new Room();

        while(true){
            Socket connectionSocket = welcomeSocket.accept();
            ProtocolHandler ph = new MultiUserChatServerHandler(connectionSocket, room);
            ph.handle();
        }

    }

    private ServerSocket welcomeSocket;
}
