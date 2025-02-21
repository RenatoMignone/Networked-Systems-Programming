package es2;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.*;

/*
 * Implementazione sequenziale erver Web
 */

public class WebServer {
 
	private int port = 8000;
	private String root = "./root";
	private ServerSocket welcomeSocket;
	
	public static void main(String argv[]) {
		try {
			//creo e avvio webserver
			WebServer server = new WebServer();
			server.go();
		} catch (IOException e) {System.err.println("The server can not be launched" + e); }
	}
	
	public WebServer() throws IOException {
		welcomeSocket = new ServerSocket(port);
	}
	
	public void go() throws IOException {
	
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			
			//creo un oggetto protocolhandler per elaborare un messaggio di richiesta HTTP
			try {
			   ProtocolHandler request = new HttpHandler(connectionSocket, root);
			   request.handle();
			} catch (IOException e) { 
				System.err.println("The request can not be processed"); 
			    connectionSocket.close();
			}
		}
    }
	
	public void configure() {
		
		//creo un oggetto File per il file di configurazione "webserver.conf"
		File conf = new File("webserver.conf");
		
		//verifico se il file esiste
		if (conf.exists()) {
			FileInputStream fis = null;
			try {
				//apro uno stream di input
				fis	= new FileInputStream(conf);
				
				//creo uno scanner per leggere il file di configurazione
				Scanner scanfis = new Scanner(fis);
				while (scanfis.hasNextLine()) {
					String confLine = scanfis.nextLine();
					if (confLine.startsWith("port")) port = Integer.parseInt(confLine.substring(4).trim());
					if (confLine.startsWith("root")) root = confLine.substring(4).trim();
				}
			} catch (FileNotFoundException e) { 
			} finally { 
				try {
					if (fis != null) fis.close();
				} catch (IOException e) { System.err.println("Error during configuration file closing"); }	
			}
		}	
		else System.out.println("Configuration file not found");
		
	}	
}

//Gestore del protocollo HTTP
class HttpHandler implements ProtocolHandler {
	
  private final static String CRLF = "\r\n";
  private Socket socket;
  private String root;	
  
  //
  //i parametri socket e root sono usati rispettivamente per recuperare gli stream associati al canale e per specificare la directory radice 
  public HttpHandler(Socket socket, String root) {
    this.socket = socket;
	this.root = root;  
  }
  
  public void handle() throws IOException {
    System.out.println("Request received from a Browser"); 
	 
    InputStream is = socket.getInputStream();
    
    DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    //stream filtro per leggere a linee
  
    Scanner sc = new Scanner(is);
    //ora si recupera dallo stream la prima linea (la request line del messaggio di richiesta)  
	System.out.println(new Date()); // Per capire se la richiesta e' nuova
	String requestLine;
	
	try {
       requestLine = sc.nextLine(); 
	} catch (NoSuchElementException e) {throw new IOException(); }   
	
    
    System.out.println("\n"+requestLine);
    
    //estraggo nome del file dalla linea di richiesta  
    StringTokenizer tokens = new StringTokenizer(requestLine);
    String method = tokens.nextToken(); 
    String fileName = tokens.nextToken();
    String line = "";
    
    
	do {
      line = sc.nextLine();
      System.out.println(line);
    } while (!line.equals(""));
	

    if (fileName.equals(File.separator)) fileName = File.separator +"index.html";  
    fileName = root + fileName;
    
    //messaggio di risposta
    String statusLine = "";
    String dateLine = "";
     
    String connectionLine = "Connection: close" + CRLF;
    String contentTypeLine = "";	
    String lastModifiedLine = "";
    

    String errorBody = "";
    File file = new File(fileName);
    
    if (file.exists()) {
      statusLine = "HTTP/1.1 200 OK" + CRLF;
      contentTypeLine = "Content-Type: " + contentType(fileName) + CRLF;
      lastModifiedLine = "Last-Modified: " + new Date(file.lastModified()) + CRLF;
      

    } else {
      statusLine = "HTTP/1.1 404 Not Found" + CRLF;
      contentTypeLine = "Content-Type: text/html" + CRLF;
      errorBody = "<HTML>" +
            "<HEAD> <TITLE>Not Found</TITLE> </HEAD>" +
            "<BODY>File Not Found2 </BODY> </HTML>";
    }
    dateLine = "Date: "+ new Date() +CRLF;

    os.writeBytes(statusLine);
    
    os.writeBytes(connectionLine);
    os.writeBytes(dateLine);
	
    if (file.exists()) {
	  os.writeBytes(lastModifiedLine);  
	  
	 	
    }
    os.writeBytes(contentTypeLine);
 
    os.writeBytes(CRLF);

    // BODY
    if (file.exists()) {
      try {
        sendFile(file, os);
      }  catch (IOException e) { 
    	  System.err.println("Errore durante l'accesso al file " + file.getName()); 
    	  // Dovremmo gestire l'errore HTTP
      }
    } else { os.writeBytes(errorBody); }
    // Eventuale chiusura della connessione nel caso di connessioni non permanenti
    if (connectionLine.equals("Connection: close" + CRLF)) {
      os.close(); socket.close(); 
      System.out.println("Connessione chiusa\n");
    }
  }


  private String contentType(String fileName) {	
   String type ="application/octet-stream";	  
   if(fileName.endsWith(".htm") || fileName.endsWith(".html"))
      type = "text/html";
    if(fileName.endsWith(".gif"))
      type = "image/gif";
    if(fileName.endsWith(".jpg") || (fileName.endsWith(".jpeg")))
      type = "image/jpeg";
  
    try {
	  type = Files.probeContentType(new File(fileName).toPath());  
	} catch (IOException e) {	}
    return type;  
  }
	

  private void sendFile(File file, OutputStream os) throws IOException {	
	  
    //utilizzo un buffer di 1KB per recuperare blocchi di
    // byte dal file ed inviarli sullo stream di rete
    FileInputStream fis = null;  
    try {
      fis = new FileInputStream(file);
      byte[] buffer = new byte[1024];
      int bytes = 0;
      //copio i blocchi dal file allo stream di output fino alla fine del file
      while((bytes = fis.read(buffer)) != -1 )  
        os.write(buffer, 0, bytes);
    }
    finally {  
      if (fis != null) fis.close();
    }	
  }
}
