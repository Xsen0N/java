package task3;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UdpChatServer {
    private Map<String, UdpMessageSender> clientMap = new HashMap<>();
    private UdpMessageReceiver receiver;

    public UdpChatServer(int port) throws IOException {
        this.receiver = new UdpMessageReceiver(port);

        Thread receiverThread = new Thread(() -> {
            try {
                while (true) {
                    String message = receiver.receiveMessage();
                    String[] tokens = message.split(":", 2);
                    if (tokens.length != 2) {
                        System.err.println("Invalid message format: " + message);
                        continue;
                    }
                    String recipient = tokens[0];
                    String text = tokens[1];
                    UdpMessageSender sender = clientMap.get(recipient);
                    if (sender == null) {
                        System.err.println("Client not found: " + recipient);
                        continue;
                    }
                    sender.sendMessage(text);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        receiverThread.start();
    }

    public synchronized void addClient(String name, UdpMessageSender sender) {
        clientMap.put(name, sender);
    }
}