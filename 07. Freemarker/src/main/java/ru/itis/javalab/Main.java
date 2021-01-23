package ru.itis.javalab;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.itis.javalab.models.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, TemplateException {
//       File file = new File("//../WEB-INF/properties/db.properties");
//        FileReader fileReader = new FileReader(file);
//        System.out.println(fileReader.read());
//        System.out.println(file.toString());
//        FileWriter fileWriter = new FileWriter("./src/main/webapp/test.html");
//        fileWriter.write("1234");
//        System.out.println(new File(".").getAbsolutePath());
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(new FileTemplateLoader(new File("D:\\JavaLab\\cource\\JavaLab_gvozdev_11-904\\07. Freemarker\\src\\main\\resources")));

        Template template = configuration.getTemplate("users.ftlh");


        List<User> users = new LinkedList<>();
        users.add(User.builder()
                .firstName("Nikita")
                .lastName("Gvozdev")
                .build());


        Map<String, Object> attributes = new HashMap<>();
        attributes.put("users", users);

//        FileWriter fileWriter = new FileWriter("output.txt");
        FileWriter fileWriter = new FileWriter("./src/main/webapp/view_users.html");
        template.process(attributes, fileWriter);


    }
}
