package task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpChatClient {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        String name = args[0];
        String serverAddress = args[1];
        int serverPort = Integer.parseInt(args[2]);

        UdpMessageSender sender = new UdpMessageSender(serverAddress, serverPort);
        UdpMessageReceiver receiver = new UdpMessageReceiver(0);
        UdpChatServerListener listener = new UdpChatServerListener(receiver);

        Thread receiverThread = new Thread(listener);
        receiverThread.start();

        try {
            sender.sendMessage(name + " has joined the chat");
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while ((message = input.readLine()) != null) {
                sender.sendMessage(name + ":" +message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}