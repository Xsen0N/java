package task3;

import java.io.IOException;

public class UdpChatServerListener implements Runnable {
    private UdpMessageReceiver receiver;
    public UdpChatServerListener(UdpMessageReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = receiver.receiveMessage();
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
