import com.beust.jcommander.JCommander;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

public class Program {
    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        String[] strings = args.files.split(",");
        if(args.mode.equals("one-thread"))
            args.count = 1;
        ThreadPool threadPool = ThreadPool.newPool(args.count);
        for (int i = 0; i < strings.length; i++) {
            int finalI = i;
            String string;
            int index;
            for(index = strings[finalI].length() - 1; index >= 0 && strings[finalI].charAt(index) != '.'; index-- );
            string = strings[finalI].substring(index + 1);

            threadPool.submit(() -> {
                try {
                    URL url = new URL(strings[finalI]);
                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();
                    String path = args.folder + finalI +  "."
                            + string;
                    Files.copy(inputStream, new File(
                            args.folder + "\\" +  finalI +  "."
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
