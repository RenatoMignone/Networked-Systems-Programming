package es2;

import java.net.Socket;


public class FactorialConcurrentServerHandler extends FactorialServerHandler implements Runnable {

    public FactorialConcurrentServerHandler(Socket socket) {
        super(socket);
        this.th = new Thread(this); //creates a new thread (givin value to the local attribute of the object)
    }

    public void handle() {
        th.start(); //uses the start method of the thread class to create a new execution environment on the machine
    }
    @Override
    public void run() {
        super.handle(); //when u call the run method, ovverrided from the implemented class, we use the local method handle 
    }

    private Thread th;
}

