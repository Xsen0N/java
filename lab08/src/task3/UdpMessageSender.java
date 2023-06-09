package task3;
import java.io.IOException;
import java.net.*;

public class UdpMessageSender {
    private DatagramSocket socket;
    private InetAddress ipAddress;
    private int port;

    public UdpMessageSender(String ipAddress, int port) throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket();
        this.ipAddress = InetAddress.getByName(ipAddress);
        this.port = port;
    }

    public void sendMessage(String message) throws IOException {
        byte[] data = message.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        socket.send(packet);
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) {
        try {
            // Create a sender with IP address and port
            UdpMessageSender sender = new UdpMessageSender("127.0.0.1", 9000);
            String message = "Hello, UDP!";
            sender.sendMessage(message); // Send a UDP message
            System.out.println("Sent message: " + message);
            sender.close(); // Close the sender
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

