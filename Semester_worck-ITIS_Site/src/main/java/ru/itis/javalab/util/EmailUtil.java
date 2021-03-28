package ru.itis.javalab.util;

import org.springframework.stereotype.Component;

/**
 * 15.02.2021
 * 19. spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component
public interface EmailUtil {
    void sendMail(String to, String subject, String from, String text);
}
