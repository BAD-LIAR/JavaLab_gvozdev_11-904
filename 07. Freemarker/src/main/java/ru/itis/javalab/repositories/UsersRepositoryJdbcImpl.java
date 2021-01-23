package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 08.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_AGE = "select * from driver where id = ?";

    //language=SQL
    private static final String SQL_SELECT = "select * from users;";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from users where id = ?";



    //language=SQL
    private static final String SQL_SAVE_USER = "insert into users (first_name, last_name) VALUES (?, ?);";





    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        template = new JdbcTemplate(dataSource);
    }

//    private SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);

    private JdbcTemplate template;

    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .id(row.getLong("id"))
            .build();


    @Override
    public List<User> getAllUsers() {
        return template.query(SQL_SELECT, userRowMapper);
    }

    @Override
    public User findUser(Integer id) {
        return template.query(SQL_FIND_BY_ID, userRowMapper, id).get(0);
    }

    @Override
    public void saveUser(String first_name, String last_name) {
        template.update(SQL_SAVE_USER, first_name, last_name);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User entity) {

    }
}
