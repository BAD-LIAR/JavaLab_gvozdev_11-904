package ru.itis.javalab.freemarker;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import ru.itis.javalab.models.User;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 09.11.2020
 * 11. Freemarker Example
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(new FileTemplateLoader(new File("src/main/resources")));

        Template template = configuration.getTemplate("users.ftlh");

//        User user = User.builder()
//                .id(1L)
//                .age(null)
//                .name("Marsel")
//                .build();

        List<User> users = new ArrayList<>();

//        users.add(User.builder().id(2L).name("Сабина").age(20).build());
//        users.add(User.builder().id(3L).name("Александра").age(19).build());
//        users.add(User.builder().id(4L).name("Анастасия").age(19).build());
//        users.add(User.builder().id(4L).name("Шамиль").age(19).build());

        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("user", user);
        attributes.put("users", users);


        FileWriter fileWriter = new FileWriter("output.html");
        template.process(attributes, fileWriter);
    }
}
