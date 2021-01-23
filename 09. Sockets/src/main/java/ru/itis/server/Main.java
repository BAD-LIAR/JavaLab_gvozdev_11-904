package ru.itis.server;

import com.beust.jcommander.JCommander;

public class Main {
    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        Integer port = args.port;
        EchoServer echoServer = new EchoServer();
        echoServer.start(port);
    }
}
