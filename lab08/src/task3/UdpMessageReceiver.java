package task3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpMessageReceiver {
    private DatagramSocket socket;
    private byte[] buffer;

    public UdpMessageReceiver(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
        this.buffer = new byte[1024];
    }

    public String receiveMessage() throws IOException {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) {
        try {
            // Create a receiver on port 5000
            UdpMessageReceiver receiver = new UdpMessageReceiver(9000);
            System.out.println("Waiting for UDP message...");
            String receivedMessage = receiver.receiveMessage(); // Receive a UDP message
            System.out.println("Received message: " + receivedMessage);
            receiver.close(); // Close the receiver
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
