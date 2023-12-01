import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Room {
    
    private List<PrintStream> list = new ArrayList<PrintStream>();

    public Room(){}
        
    public synchronized void broadcast(String msg){

        for(PrintStream ps: list){
            ps.println(msg);
        }
    }

    public synchronized void add(Socket s) throws IOException{
        list.add(new PrintStream(s.getOutputStream()));
    }

}