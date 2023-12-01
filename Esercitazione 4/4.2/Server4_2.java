import java.io.*; 
import java.net.*; 
import java.util.*;
import java.nio.*; 

class DatagramServer { 
    public static void main(String args[]) throws Exception { 
        DatagramSocket serverSocket = new DatagramSocket(6200); 

        byte[] receiveData = new byte[1024]; 
        byte[] sendData; 
        int a,b;
        long prod;

        while(true) { 
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
  
            serverSocket.receive(receivePacket);

            ByteBuffer buf = ByteBuffer.wrap(receivePacket.getData());

            a = buf.getInt();

            b = buf.getInt();
            
            prod = a * (long) b;

            //buf.rewind();
            buf = ByteBuffer.allocate(8);

            buf.putLong(prod);

            sendData = buf.array();

            InetAddress IPAddress = receivePacket.getAddress(); 
            int port = receivePacket.getPort(); 

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); 
            serverSocket.send(sendPacket); 
        } 
    } 
}