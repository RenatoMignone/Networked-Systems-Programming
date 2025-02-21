package es3.coordinated;

public class SimpleBuffer {
	   private int buf;
	   private boolean dataAvailable = false;
	   
	   public synchronized int get() throws InterruptedException {
		   while(!dataAvailable) wait();
		   dataAvailable = false;
		   notifyAll();
	       return buf;
	   }
	   
	   public synchronized void put (int value) throws InterruptedException {
	       while(dataAvailable) wait();
		   buf = value;
	       dataAvailable = true;
	       notifyAll();
	   }
	}