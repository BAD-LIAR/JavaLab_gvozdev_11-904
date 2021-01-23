package ru.itis.client;

import com.beust.jcommander.JCommander;

public class Main {
    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        String ip = args.serverIP;
        Integer port = args.serverPort;
        MainForClient.start(ip, port);
    }
}
