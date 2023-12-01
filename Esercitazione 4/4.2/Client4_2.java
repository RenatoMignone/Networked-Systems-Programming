import java.io.*; 
import java.net.*; 
import java.util.*;
import java.nio.*;

class DatagramClient { 
    public static void main(String args[]) throws Exception { 

        Scanner inFromUser = new Scanner(System.in); 
        //il numero di porta viene assegnato dinamicamente con la chiamata alla creazione dell oggetto
        DatagramSocket clientSocket = new DatagramSocket(); 

        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");

        byte[] sendData; 
        byte[] receiveData = new byte[1024]; 
        int a,b;
        long prod;

        System.out.println("Inserisci il primo intero: ");

        a = inFromUser.nextInt();

        System.out.println("Inserisci il secondo intero: ");

        b = inFromUser.nextInt();

        // DataOutputStream bufToServer =  new DataOutputStream(new ByteArrayOutputStream());;
        // DataOutputStream shortStreamToServer = new DataOutputStream(bufToServer);

        // shortStreamToServer.writeInt(a);
        // shortStreamToServer.writeInt(b);

        // sendData = bufToServer.toByteArray();
        

        //creo bytebuffer per creare un buffer e metterci dentro gli interi
        ByteBuffer buf = ByteBuffer.allocate(8);
        buf.putInt(a);
        buf.putInt(b);

        sendData = buf.array();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 6200); 
        clientSocket.send(sendPacket); 

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
        clientSocket.receive(receivePacket); 

        buf = ByteBuffer.wrap(receivePacket.getData());

        // ByteArrayInputStream bufferFromServer = new ByteArrayInputStream(receivePacket.getData());
        // DataInputStream shortStreamFromServer = new DataInputStream(bufferFromServer);

        prod = buf.getLong();

        System.out.println("The multiplication of " + a + " and " + b + " is: " + prod);
        // inFromUser.close();
        clientSocket.close(); 
        inFromUser.close();
    } 
}