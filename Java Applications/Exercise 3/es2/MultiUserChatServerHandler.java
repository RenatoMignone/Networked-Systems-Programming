import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class MultiUserChatServerHandler extends Thread implements ProtocolHandler{
    
    public MultiUserChatServerHandler(Socket s, Room r) throws IOException{
        mySock = s;
        room = r;
        room.add(mySock);
    }

    public void handle(){
        start();
    }

    public void run(){
        try{
            Scanner input = new Scanner(mySock.getInputStream());
            PrintStream outToClient = new PrintStream(mySock.getOutputStream());
            boolean end = false;
            do{
                
                String line = input.nextLine();
                end = line.endsWith(".");
                room.broadcast(!end ? line : line.substring(0, line.lastIndexOf('.')));

            }while(!end);
            outToClient.print(".");
        }catch(IOException e){ System.err.println("errore I/O" + e);
        }catch (Exception e){;
        }finally { try{ mySock.close(); } catch(IOException e) {}}
    }

    private Socket mySock;
    private Room room;

}
