package ru.itis.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Client {
    private Socket client;

    private PrintWriter toServer;
    private BufferedReader fromServer;

    public Client(String host, int port){
        try {
            client = new Socket(host, port);
            toServer = new PrintWriter(client.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            new Thread(receiverMessageTask).start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sentMessage(String message){
        toServer.println(message);
    }

    private Runnable receiverMessageTask = () -> {
        while (true){
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    System.out.println("From server: " + messageFromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}
