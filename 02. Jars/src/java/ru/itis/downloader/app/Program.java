package ru.itis.downloader.app;

import com.beust.jcommander.JCommander;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import ru.itis.downloader.util.Args;
import ru.itis.downloader.util.ThreadPool;

public class Program {
    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        String[] strings = args.files.split(",");
        ThreadPool threadPool = ThreadPool.newPool(args.count);
        for (int i = 0; i < strings.length; i++) {
            int finalI = i;
            String string;
            int index;
            for(index = strings[finalI].length(); index >= 0 && strings[finalI].charAt(index) != '.'; index-- );
            string = strings[finalI].substring(index + 1);

            threadPool.submit(() -> {
                try {
                    URL url = new URL(strings[finalI]);
                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();
                    Files.copy(inputStream, new File(
                            args.folder + finalI +  "."
                                    + string).toPath());
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException();
                } catch (IOException e) {
                    throw new IllegalArgumentException();
                }
            });
        }
    }
}
