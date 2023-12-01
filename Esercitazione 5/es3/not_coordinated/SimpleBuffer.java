package es3.not_coordinated;

public class SimpleBuffer {
	   private int buf;
	   
	   public int get() {
	       return buf;
	   }
	   public void put (int value) {
	       buf = value;
	   }
	}