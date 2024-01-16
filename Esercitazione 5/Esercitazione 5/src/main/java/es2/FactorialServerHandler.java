package es2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FactorialServerHandler implements ProtocolHandler {

    public FactorialServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void handle() {
        try {
            DataInputStream inFromClient = new DataInputStream(this.socket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(this.socket.getOutputStream());
            int n; long fact;

            do {
                n = inFromClient.readInt();
                fact = this.factorial(n);
                outToClient.writeLong(fact);
            } while(n > 0);

        } catch(IOException e) {
            System.err.println("SOCKET ERROR");
            e.printStackTrace();
        } finally {
            if (socket == null) try { socket.close(); } catch (IOException e) {}
        }
    }

    private long factorial(int n) {
        if(n < 1) return 1;
        return n * factorial(n - 1);
    }

    private Socket socket;
}
