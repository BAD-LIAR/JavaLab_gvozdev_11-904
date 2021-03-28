package ru.itis.javalab.repositories.old;

import ru.itis.javalab.models.User;

/**
 * 08.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersRepository extends CrudRepository<User> {
    boolean containsUser(String email, String password);
}
