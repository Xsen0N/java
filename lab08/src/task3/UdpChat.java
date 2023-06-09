package task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpChat {
    public static void main(String[] args) {
        try {
            UdpMessageReceiver receiver = new UdpMessageReceiver(5000);
            UdpMessageSender sender = new UdpMessageSender("localhost", 5000);

            Thread receiverThread = new Thread(() -> {
                try {
                    while (true) {
                        String message = receiver.receiveMessage();
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread senderThread = new Thread(() -> {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                    String message;
                    while ((message = input.readLine()) != null) {
                        sender.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            receiverThread.start();
            senderThread.start();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}