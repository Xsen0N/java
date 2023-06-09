package client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {
    public static void main(String[] args) throws UnknownHostException {
        Socket socket = null;
        BufferedReader br = null;
        try {
// установка соединения с сервером
            socket = new Socket(InetAddress.getLocalHost(), 7071);
            PrintStream ps =
                    new PrintStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            for (int i = 1; i <= 10; i++) {
                ps.println("PING");
                System.out.println(br.readLine());
                Thread.sleep(1_000);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
