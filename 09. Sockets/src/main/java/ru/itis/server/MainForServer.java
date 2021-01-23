package ru.itis.server;

public class MainForServer {
    public static void main(String[] args) {
        EchoServer echoServer = new EchoServer();
        echoServer.start(43214);
    }
}
