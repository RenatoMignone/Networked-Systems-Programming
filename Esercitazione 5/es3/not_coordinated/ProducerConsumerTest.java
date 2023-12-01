package es3.not_coordinated;

public class ProducerConsumerTest {
    public static void main(String[] args) {
        SimpleBuffer sb = new SimpleBuffer();
        Producer p1 = new Producer(sb, 1);
        Consumer c1 = new Consumer(sb, 1);
        p1.start();
        c1.start();
    }  
}