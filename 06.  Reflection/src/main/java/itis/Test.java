package itis;

import itis.homework.EntityManager;
import itis.homework.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class Test {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        DataSource dataSource = context.getBean("dataSource", DataSource.class);

        EntityManager entityManager = new EntityManager(dataSource);

        //entityManager.createTable("user", User.class);
//        User user = new User((long) 1, "Nikita", "Gvozdev", false);
//        entityManager.save("user", user);
        User user = entityManager.findById("\"user\"", User.class, Long.class, 1L);
        int i = 0;
    }
}
