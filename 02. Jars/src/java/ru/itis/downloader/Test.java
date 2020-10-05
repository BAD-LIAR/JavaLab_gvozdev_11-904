package java.ru.itis.downloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.ru.itis.downloader.util.ThreadPool;

public class Test {
    public static void main(String[] args) {
//        String url_string = "https://sun9-32.userapi.com/c840626/v840626661/9586/KYJq-ZklpQE.jpg";
//        ThreadPool threadPool = ThreadPool.newPool(3);
//        for (int i = 0; i < 5; i++) {
//            int finalI = i;
//            String string;
//            int index;
//            for (index = url_string.length(); index >= 0 && url_string.charAt(index) != '.'; index--) ;
//            string = url_string.substring(index + 1);
//
//            threadPool.submit(() -> {
//                try {
//                    URL url = new URL("\"" + url_string + "\"");
//                    URLConnection connection = url.openConnection();
//
//                    InputStream inputStream = connection.getInputStream();
//                    Files.copy(inputStream, new File(
//                            "\"" + "D:/JavaLab/cource/02. Jars/src/java/ru/itis/downloader/photos" + finalI + "."
//                                    + string + "\"").toPath());
//                } catch (MalformedURLException e) {
//                    throw new IllegalArgumentException();
//                } catch (IOException e) {
//                    throw new IllegalArgumentException();
//                }
//            });
//        }
    }
}
