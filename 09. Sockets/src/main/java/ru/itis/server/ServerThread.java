package ru.itis.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {

    private BufferedReader fromClient;
    private PrintWriter toClient;
    Socket client;

    public ServerThread(Socket client) {
        try {
            this.client = client;
            fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toClient = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void run() {
        try {
            while (!client.isClosed()) {
                String message = fromClient.readLine();
                if (message != null) {
                    System.out.println("From client: " + message);
                    EchoServer.mailing(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

