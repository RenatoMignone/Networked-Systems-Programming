package es3;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WebServer {

    private int port = 8026;
    private String root = "./root";
    private ServerSocket welcomeSocket;

    public static void main(String argv[]) {
        try {
            WebServer server = new WebServer();
            server.go();
        } catch (IOException e) {
            System.err.println("The server can not be launched" + e);
        }
    }

    public WebServer() throws IOException {
        welcomeSocket = new ServerSocket(port);
    }

    public void go() throws IOException {

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            try {
                ProtocolHandler request = new ConcurrentHttpHandler(connectionSocket, root);
                request.handle();
            } catch (IOException e) {
                System.err.println("The request can not be processed");
                connectionSocket.close();
            }
        }
    }

    public void configure() {
        File conf = new File("webserver.conf");
        if (conf.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(conf);
                Scanner scanfis = new Scanner(fis);
                while (scanfis.hasNextLine()) {
                    String confLine = scanfis.nextLine();
                    if (confLine.startsWith("port")) port = Integer.parseInt(confLine.substring(4).trim());
                    if (confLine.startsWith("root")) root = confLine.substring(4).trim();
                }
            } catch (IOException e) {
            } finally {
                try {
                    if (fis != null) fis.close();
                } catch (IOException e) {
                    System.err.println("Error during configuration file closing");
                }
            }
        } else System.out.println("Configuration file not found");

    }
}

class ConcurrentHttpHandler extends HttpHandler implements Runnable {
    private Thread activeHandler;

    // I parametri socket e root sono usati rispettivamente per recuperare gli stream associati al canale e per specificare la directory radice
    //questi parametri socket e root li utilizziamo per recuperare gli stream associati al det. canale e specificare la directory radice
    public ConcurrentHttpHandler(Socket socket, String root) {
        super(socket, root);
        activeHandler = new Thread(this);
    }

    public void handle() {
        activeHandler.start();
    }

    public void run() {
        super.handle();
    }
}

//gestore del protocollo HTTP con il metodo GET per elaborare le richieste client
class HttpHandler extends Thread implements ProtocolHandler {
    private final static String CRLF = "\r\n";
    private Socket socket;
    private String root;

    
    //costruttore
    public HttpHandler(Socket socket, String root) {
        this.socket = socket;
        this.root = root;
    }

    public void handle() {
        this.start();
    }

    public void run() {
        System.out.println("Request received from a Browser");  
        try {
            InputStream is = socket.getInputStream();
            DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            Scanner sc = new Scanner(is);
            System.out.println(new Date()); //per vedere se la req è nuova
            String requestLine;

            try {
                requestLine = sc.nextLine();
            } catch (NoSuchElementException e) {
                throw new IOException();
            }


            System.out.println("\n" + requestLine);

            //estrae il nome del file dalla linea di richiesta
            StringTokenizer tokens = new StringTokenizer(requestLine);
            
            //recupero il metodo dal primo token della request line
            String method = tokens.nextToken();
            
            //recupero il nome della risorsa richiesta dal secondo token della request line
            String fileName = tokens.nextToken();
            
            //visualizzo sullo std out il resto del contenuto del messaggio di richiesta
            String line = "";

            //salto tutte le linee di intestazione visualizzandole sullo stdout
            do {
                line = sc.nextLine();
                System.out.println(line);
            } while (!line.equals(""));

     
            //msg di risposta
            if (fileName.equals(File.separator)) fileName = File.separator + "index.html";
            fileName = root + fileName;

            //msg di risposta 
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
            dateLine = "Date: " + new Date() + CRLF;

            os.writeBytes(statusLine);
            os.writeBytes(connectionLine);
            os.writeBytes(dateLine);

            if (file.exists()) {
                os.writeBytes(lastModifiedLine);
	
            }
            os.writeBytes(contentTypeLine);
            os.writeBytes(CRLF);

            if (file.exists()) {
                try {
                    sendFile(file, os);
                } catch (IOException e) {
                    System.err.println("Errore durante l'accesso al file " + file.getName());
                }
            } else {
                os.writeBytes(errorBody);
            }
            //gestisco un eventuale chiusura della connessione nel caso di connessioni non permanenti
            if (connectionLine.equals("Connection: close" + CRLF)) {
                os.close();
                socket.close();
                System.out.println("Connessione chiusa\n");
            }
        } catch (IOException e1) {
        }
    }


    private String contentType(String fileName) {
        String type = "application/octet-stream";
        if (fileName.endsWith(".htm") || fileName.endsWith(".html"))
            type = "text/html";
        if (fileName.endsWith(".gif"))
            type = "image/gif";
        if (fileName.endsWith(".jpg") || (fileName.endsWith(".jpeg")))
            type = "image/jpeg";

        try {
            type = Files.probeContentType(new File(fileName).toPath());
        } catch (IOException e) {
        }
        return type;
    }


    private void sendFile(File file, OutputStream os) throws IOException {
      
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytes = 0;
            while ((bytes = fis.read(buffer)) != -1)
                os.write(buffer, 0, bytes);
        } finally {
            if (fis != null) fis.close();
        }
    }
}


