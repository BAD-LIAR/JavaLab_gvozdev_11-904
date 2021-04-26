package ru.itis.javalab.security.details;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.javalab.models.Token;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.TokensRepository;


import java.util.function.Supplier;

/**
 * 13.03.2021
 * 03. Spring Security
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component("tokenUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TokensRepository tokensRepository;


    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        User user = null;
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("seckret_key"))
                    .build()
                    .verify(token);

            user = User.builder()
                    .email(decodedJWT.getClaim("email").asString())
                    .role(decodedJWT.getClaim("role").as(User.Role.class))
                    .state(decodedJWT.getClaim("state").as(User.State.class))
                    .build();

        } catch (Throwable throwable) {
            throw new IllegalArgumentException(throwable);
        }
        return new UserDetailsImpl(user);
    }
}
