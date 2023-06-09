package client_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetServerThread {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(7071);
            System.out.println("initialized");
            while (true) {
// ожидание клиента
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress().getHostName()
                        + " connected");
                /*
                 * создание отдельного потока для обмена данными
                 * с соединившимся клиентом
                 */
                Server thread = new Server(socket);
// запуск потока
                thread.start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}