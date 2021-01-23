package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.List;

/**
 * 15.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersService {
    List<User> getAllUsers();
    User findUser(Integer id);

    void saveUser(String first_name, String last_name);
}
