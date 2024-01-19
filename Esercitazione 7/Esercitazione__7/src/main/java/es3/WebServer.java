package es3;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.*;

// Implementazione sequenzale di un semplice Server Web
public class WebServer {
    // Valori di default per il numero di porta e per il percorso radice
    private int port = 8000;
    private String root = "./root";
    private ServerSocket welcomeSocket;
    public static void main(String argv[]) {
        try {
            WebServer server = new WebServer();
            server.start();
        } catch (IOException e) {System.err.println("The server can not be launched" + e); }
    }
    public WebServer() throws IOException {
        // configure();
        welcomeSocket = new ServerSocket(port);
    }
    public void start() throws IOException {
        // Si crea una socket di tipo server

        // Elabora le richieste http in un loop infinito
        while (true) {
            // Accetta richieste di connessione
            Socket connectionSocket = welcomeSocket.accept();
            HttpHandler request = new HttpHandler(connectionSocket, root);
            request.start();
        }
    }

    public void configure() {
        // Eventuale recupero del numero di porta e del percorso radice dal file di configurazione
        File conf = new File("webserver.conf");
        if (conf.exists()) {
            FileInputStream fis = null;
            try {
                fis	= new FileInputStream(conf);
                Scanner scanfis = new Scanner(fis);
                while (scanfis.hasNextLine()) {
                    String confLine = scanfis.nextLine();
                    if (confLine.startsWith("port")) port = Integer.parseInt(confLine.substring(4).trim());
                    if (confLine.startsWith("root")) root = confLine.substring(4).trim();
                }
            } catch (FileNotFoundException e) { // Never reached
            } finally {
                try {
                    if (fis != null) fis.close();
                } catch (IOException e) { System.err.println("Error during configuration file closing"); }
            }
        }
        else System.out.println("Configuration file not found");

    }

}



// Gestore del protocollo HTTP. Versione in grado di elaborare solo richieste con metodo GET
class HttpHandler extends Thread {
    private final static String CRLF = "\r\n";
    private Socket s;
    private String root;

    public HttpHandler(Socket s,String root) {
        this.s=s;
        this.root=root;

    }

    public void run() {

        System.out.println("Request received from a Browser");
        try {
            s.setSoTimeout(500);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            InputStream in= s.getInputStream();

            DataOutputStream os=new DataOutputStream(s.getOutputStream());
            Scanner sc=new Scanner(in);
            System.out.println(new Date());
            String reqLine;
            try {
                reqLine=sc.nextLine();
            }catch(NoSuchElementException e) {
                throw new IOException();
            }
            if(reqLine==null) throw new IOException();

            System.out.println("\n"+reqLine);

            StringTokenizer tokens = new StringTokenizer(reqLine);

            String method = tokens.nextToken();

            String fileName = tokens.nextToken();

            String line = "";

            do {
                line = sc.nextLine();
                System.out.println(line);
            } while (!line.equals(""));

            if (fileName.equals(File.separator)) fileName = File.separator +"index.html";

            fileName = root + fileName;

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

            if (file.exists()) {
                try {
                    sendFile(file, os);
                }  catch (IOException e) {
                    System.err.println("Errore durante l'accesso al file " + file.getName());

                }
            } else { os.writeBytes(errorBody); }

            if (connectionLine.equals("Connection: close" + CRLF)) {
                os.close(); s.close();
                System.out.println("Connessione chiusa\n");
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    private void sendFile(File file, OutputStream os) throws IOException {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytes = 0;

            while((bytes = fis.read(buffer)) != -1 )
                os.write(buffer, 0, bytes);
        }
        finally {
            if (fis != null) fis.close();
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

}