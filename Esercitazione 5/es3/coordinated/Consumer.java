package es3.coordinated;

public class Consumer extends Thread {
    private SimpleBuffer sbuf;
    private int id;

    public Consumer(SimpleBuffer sb, int id) {
        sbuf = sb;
        this.id = id;
    }

    public void run() {
        int value = 0;
        for (int i = 0; i < 10; i++) {
            try {
				value = sbuf.get();
			} catch (InterruptedException e1) {}
            System.out.println("Consumer #" + this.id + " got: " + value);
            try { sleep((int)(Math.random() * 100)); } catch (InterruptedException e) { }
        }
    }  
}