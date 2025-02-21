package es1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WebServer {
	
	 private ServerSocket welcomeSocket;
	 private final int port = 8000;
	 public final String root = ".root";
    
    public static void main(String[] args){
        
        try{
            WebServer server = new WebServer();
            server.go();
        }catch(IOException e){
            System.err.println("The server cannot be launched");
        }
    }

    // Costruttore della classe WebServer
    public WebServer() throws IOException{
        //Creao ServerSocket associato alla porta specificata
    	welcomeSocket = new ServerSocket(port);
    }

    public void go() throws IOException{
        //loop infinito
        while(true){
        	
            //accetto una connessione da un client
            Socket connectionSocket = welcomeSocket.accept();
            try{
                //creo un nuovo gestore di protocollo per gestire la richiesta HTTP
                ProtocolHandler request = new HttpHandler(connectionSocket, root);
                
                request.handle();
            }catch(IOException e){
                System.err.println("The request cannot be processed");
                connectionSocket.close();
            }
        }
    }
}

//Utilizzo questa classe per gestire la richiesta HTTP
class HttpHandler implements ProtocolHandler {
    private Socket socket;
    private String root;

    public HttpHandler(Socket socket, String root){
        this.socket = socket;
        this.root = root;
    }

    // Implementazione del metodo handle dell'interfaccia ProtocolHandler
    public void handle() throws IOException{
    	

        System.out.println("Request received from a browser");

        //leggo lo stream dal socket
        InputStream is = socket.getInputStream();
        
        //creo uno scanner per leggere la richiesta HTTP
        Scanner sc = new Scanner(new InputStreamReader(is));

        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        //stampo la data e l'ora della richiesta
        System.out.println(new Date());

        //leggo la prima linea del messaggio di richiesta HTTP
        String requestLine;
        try{
            requestLine = sc.nextLine();
        }catch(NoSuchElementException e){
            throw new IOException();
        }

        //stampo la prima linea della richiesta
        System.out.println("\n" + requestLine);

        String line = "";
        
         //estraggo il contenuto (linee) dal messaggio di richiesta HTTP
         
        do{
            line = sc.nextLine();
            System.out.println(line);
        }while(!line.equals(""));

        System.out.println("Processed");
    }
}





