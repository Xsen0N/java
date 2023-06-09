package chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static ServerSocket serverSocket;
    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(5000); // set up the server socket
            System.out.println("Chat server started on port 5000");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // wait for a client to connect
                System.out.println("New client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket); // create a new client handler
                clients.add(clientHandler); // add the new client to the list
                clientHandler.start(); // start the client handler
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendToClient(ClientHandler sender, String recipientName, String message) {
        for (ClientHandler client : clients) {
            if (client.clientName.equals(recipientName)) {
                client.sendMessage(sender.clientName, message); // send the message to the recipient
                return;
            }
        }

        sender.sendMessage("Server", "Error: recipient not found"); // notify the sender if the recipient was not found
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String clientName;

        public ClientHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Enter your name: ");
            clientName = in.readLine(); // get the client's name
            out.println("Welcome to the chat, " + clientName + "!");
        }

        public void run() {
            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) { // read messages from the client
                    if (inputLine.startsWith("@")) { // if the message is directed to another client
                        int recipientIndex = inputLine.indexOf(" ");
                        String recipientName = inputLine.substring(1, recipientIndex);
                        String message = inputLine.substring(recipientIndex + 1);
                        sendToClient(this, recipientName, message); // send the message to the recipient
                    } else {
                        sendMessage(clientName, inputLine); // send the message to all clients
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendMessage(String senderName, String message) {
            out.println(senderName + ": " + message); // send a message to the client
        }
    }
}
