package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);
}
