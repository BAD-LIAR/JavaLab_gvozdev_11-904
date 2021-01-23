package ru.itis.client;

import java.util.Scanner;

public class MainForClient {
    public static void start(String ip, Integer port) {
        Client client = new Client(ip, port);
        while (true){
            Scanner sc = new Scanner(System.in);
            client.sentMessage(sc.nextLine());
        }
    }
}
