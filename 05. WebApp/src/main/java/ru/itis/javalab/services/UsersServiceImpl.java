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
        return usersRepository.findAll();
    }

    public boolean findUser(String login, String password) {
        return usersRepository.findUser(login, password);

    }



    @Override
    public User getUser(String login, String password) {
        return usersRepository.getUser(login, password);
    }

    @Override
    public User getUser(String login) {
        return usersRepository.findUser(login);
    }

    @Override
    public boolean findUser(String login) {
        return usersRepository.findUSer(login);
    }

    @Override
    public void saveUser(String login, String password) {
        User user = User.builder().
                login(login).
                password(password).
                build();
        usersRepository.save(user);
    }
}
