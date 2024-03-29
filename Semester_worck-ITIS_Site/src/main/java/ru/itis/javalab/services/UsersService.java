package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.dto.UserForm;

import java.util.List;

/**
 * 15.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersService {
    List<UserDto> getAllUsers();
    void addUser(UserForm userDto);

    UserDto getUser(Long userId);
    boolean containsUser(String email, String password);
}
