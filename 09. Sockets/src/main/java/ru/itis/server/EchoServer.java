package ru.itis.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class EchoServer {
    private static List<Socket> clients = new LinkedList<Socket>();


    public void start(int port) {
        ServerSocket server;

        try {
            server = new ServerSocket(port);
            while (true) {
                Socket client = server.accept();
                clients.add(client);
                new Thread(new ServerThread(client)).start();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void mailing(String message) {
        PrintWriter printWriter;
        for (Socket client : clients){
            if(!client.isClosed()){
                try {
                    printWriter = new PrintWriter(client.getOutputStream(), true);
                    printWriter.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
