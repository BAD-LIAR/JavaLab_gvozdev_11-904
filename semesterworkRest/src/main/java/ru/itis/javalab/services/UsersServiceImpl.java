package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.itis.javalab.dto.UserDto.from;

/**
 * 15.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }



    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public List<UserDto> getAllUsers() {
        return from(usersRepository.findAll());
    }



    @Override
    public void addUser(UserDto userDto) {
        User newUser = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .confirmCode(UUID.randomUUID().toString())
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .build();
        usersRepository.save(newUser);


    }

    @Override
    public UserDto getUser(Long userId) {
        return from(usersRepository.findById(userId).orElse(null));
    }

    @Override
    public boolean containsUser(String email, String password) {
        return !usersRepository.findByEmailAndPassword(email, password).isEmpty();
    }

    @Override
    public Optional<User> findByEmail(String email){
        return usersRepository.findByEmail(email);
    }


}