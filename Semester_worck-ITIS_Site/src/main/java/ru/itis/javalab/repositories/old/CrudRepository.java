package ru.itis.javalab.repositories.old;

import java.util.List;
import java.util.Optional;

/**
 * 08.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface CrudRepository<T> {
    List<T> findAll();
    Optional<T> findById(Long id);

    void save(T entity);
    void update(T entity);
    void deleteById(Long id);
    void delete(T entity);


}
