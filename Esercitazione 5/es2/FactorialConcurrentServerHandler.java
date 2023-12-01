package es2;

import java.net.Socket;


public class FactorialConcurrentServerHandler extends FactorialServerHandler implements Runnable {

    public FactorialConcurrentServerHandler(Socket socket) {
        super(socket);
        this.th = new Thread(this);
    }

    public void handle() {
        th.start();
    }
    @Override
    public void run() {
        super.handle();
    }

    private Thread th;
}

