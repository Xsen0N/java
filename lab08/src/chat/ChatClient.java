package chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private static final String SERVER_ADDRESS = "192.168.43.154";
    private static final int SERVER_PORT = 5000;

    private BufferedReader in;
    private PrintWriter out;

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.run();
    }

    public void run() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT); // connect to the server
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String name = getName(); // get the user's name
            out.println(name); // send the name to the server

            System.out.println("Connected to the chat server");

            Thread readThread = new Thread(new ReadThread()); // start a new thread to read messages from the server
            readThread.start();

            Thread writeThread = new Thread(new WriteThread()); // start a new thread to write messages to the server
            writeThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getName() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your name: ");
        return input.readLine();
    }

    private void sendMessage(String message) {
        out.println(message);
    }

    private class ReadThread implements Runnable {
        @Override
        public void run() {
            try {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) { // read messages from the server
                    System.out.println(serverMessage); // print the message to the console
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class WriteThread implements Runnable {
        @Override
        public void run() {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                String userMessage;
                while ((userMessage = input.readLine()) != null) { // read messages from the console
                    sendMessage(userMessage); // send the message to the server
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
