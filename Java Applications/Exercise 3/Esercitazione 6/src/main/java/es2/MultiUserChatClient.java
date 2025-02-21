package es2;

import java.io.IOException;
import java.net.Socket;
import es2.ProtocolHandler;

public class MultiUserChatClient {
    public static void main(String[] args) {
        try{
            MultiUserChatClient multi = new MultiUserChatClient("127.0.0.1", 32500);
            multi.go();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public MultiUserChatClient(String s, int port) throws Exception{
        clientSocket = new Socket(s, port);
    }

    public void go() throws IOException{
        MultiUserChatClientHandler ph = new MultiUserChatClientHandler(clientSocket);
        ph.handle();
    }

    private Socket clientSocket;
}