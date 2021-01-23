package ru.itis.javalab;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       File file = new File("//../WEB-INF/properties/db.properties");
        FileReader fileReader = new FileReader(file);
        System.out.println(fileReader.read());
        System.out.println(file.toString());
    }
}
