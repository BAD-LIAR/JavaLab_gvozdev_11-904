package ru.itis.client;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = {"--server-ip"})
    public String serverIP;

    @Parameter(names = {"--server-port"})
    public Integer serverPort;

}
