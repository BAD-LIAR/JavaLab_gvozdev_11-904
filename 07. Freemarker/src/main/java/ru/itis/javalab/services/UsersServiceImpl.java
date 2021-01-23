package ru.itis.javalab.services;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;

/**
 * 15.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return usersRepository.getAllUsers();
    }

    @Override
    public User findUser(Integer id) {
        return usersRepository.findUser(id);
    }

    @Override
    public void saveUser(String first_name, String last_name) {
        usersRepository.saveUser(first_name, last_name);
    }
}
