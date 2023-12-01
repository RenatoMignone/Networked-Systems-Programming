class PrinterR implements Runnable{
    private String message;

    public PrinterR(String s){
        message = s;
    }

    @Override
    public void run(){
        for (int i = 0; i<100;i++){
            System.out.println(i +"" +message);
        }
    }
}
public class PrinterTest2{
    public static void main(String[] args){
        Thread p1 = new Thread(new PrinterR(" Sono il thread 1"));
        Thread p2 = new Thread(new PrinterR(" Sono il thread 2"));
        p1.start();
        p2.start();
        System.out.println("Main terminated");
    }
}