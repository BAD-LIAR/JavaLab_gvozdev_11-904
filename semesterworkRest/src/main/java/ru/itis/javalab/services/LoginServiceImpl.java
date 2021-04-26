package ru.itis.javalab.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.EmailPasswordDto;
import ru.itis.javalab.dto.TokenDto;
import ru.itis.javalab.models.Token;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.TokensRepository;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * 05.04.2021
 * 21. REST API
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokensRepository tokensRepository;


    @Override
    public TokenDto login(EmailPasswordDto emailPassword) {
        User user = null;
        try {
            user = usersRepository.findByEmail(emailPassword.getEmail())
                    .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }
        if (passwordEncoder.matches(emailPassword.getPassword(), user.getPassword())) {
            String tokenValue = JWT.create()
                    .withSubject(user.getId().toString())
                    .withClaim("role", user.getRole().toString())
                    .withClaim("state", user.getState().toString())
                    .withClaim("email", user.getEmail())
                    .sign(Algorithm.HMAC256("seckret_key"));
            Token token = Token.builder()
                    .token(tokenValue)
                    .user(user)
                    .build();

            tokensRepository.save(token);

            return TokenDto.builder()
                    .token(tokenValue)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
