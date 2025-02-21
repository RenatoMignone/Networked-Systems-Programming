class Printer extends Thread {
    private String message;

    public Printer(String s) {
        message = s;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " " + message);
        }
    }
}

public class PrinterTester1 {
    public static void main(String[] args) {
        Printer p1 = new Printer("Thread 1");
        Printer p2 = new Printer("Thread 2");
        p1.start();
        p2.start();
        System.out.println("Main terminated");
    }
}

