package es2;

import es1.ProtocolHandler;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class MultiUserChatClientHandler implements ProtocolHandler {

    public MultiUserChatClientHandler(Socket socket){
        this.socket = socket;
    }

    public void handle(){
        Reader reader = new Reader(socket);
        Writer writer = new Writer(socket);

        writer.start();
        reader.start();
        
    }

    private Socket socket;
}

class Writer extends Thread{
    public Writer(Socket socket){ 
        this.socket = socket;
    }

    public void run(){
        Scanner inFromUser = null;
        try{
            inFromUser = new Scanner(System.in);

            PrintStream outToServer = new PrintStream(socket.getOutputStream());
            String line;
            System.out.println("Inserisci il tuo nome: ");
            String nick = inFromUser.nextLine();

            do{
                line = inFromUser.nextLine();
                outToServer.println(nick + ": " + line);
            }while(!line.endsWith("."));
            System.out.println("Connessioen terminata");
        }catch(IOException e){
            e.printStackTrace();
        }finally{

            try{
                if(socket != null)
                socket.shutdownOutput();
            }
            catch(IOException e){
                e.printStackTrace();
            }

            if(inFromUser != null)
                inFromUser.close();
        }
    }

    private Socket socket;
}

class Reader extends Thread{
    public Reader(Socket s){ socket = s; }

    public void run(){
        Scanner inFromSever = null;
        try{
            PrintStream outToUser = System.out;
            inFromSever = new Scanner(socket.getInputStream());
            String line;
            do{
                line = inFromSever.nextLine();
                outToUser.println(line);
            }while(!line.endsWith("."));
            System.out.println("Connessione terminata");
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            System.out.println(e);
        }finally{

            try{
                if(socket != null)
                socket.shutdownInput();
            }
            catch(IOException e){
                e.printStackTrace();
            }
                if(inFromSever != null)
                inFromSever.close();
        }
    }

    private Socket socket;
    
}
