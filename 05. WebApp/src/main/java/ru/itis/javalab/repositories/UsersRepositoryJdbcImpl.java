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
    private static final String SQL_SELECT = "select * from ";



    //language=SQL
    private static final String SQL_FIND_BY_LOGIN_AND_PASSWORD = "select * from users where login = ? and password = ?";

    //language=SQL
    private static final String SQL_FIND_BY_AUTH = "select * from users where auth = ?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from users where id = ?";

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into users (login, password) values (?, ?);";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from users where id = ?";

    //language=SQL
    private static final String SQL_DELETE_USER = "delete from users where login = ?";
    //language=SQL
    private static final String SQL_FIND_BY_LOGIN = "select * from users where login = ?";





    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        template = new JdbcTemplate(dataSource);
    }

//    private SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);

    private JdbcTemplate template;

    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .firstName(row.getString("name"))
            .lastName(row.getString("lastname"))
            .id(row.getLong("id"))
            .password(row.getString("password"))
            .login(row.getString("login"))
            .build();

    @Override
    public List<User> findAllByAge(Integer age) {
        return template.query(SQL_SELECT_BY_AGE, userRowMapper, age);
    }

    @Override
    public Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_SELECT, userRowMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {
        template.update(SQL_INSERT_USER, entity.getLogin(), entity.getPassword());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {
        template.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public void delete(User entity) {
        template.update(SQL_DELETE_USER, entity.getLogin(), entity.getPassword());
    }

    public boolean findUser(String login, String password) {
        return !template.query(SQL_FIND_BY_LOGIN_AND_PASSWORD, userRowMapper, login, password).isEmpty();

    }

    public User getUser(String login, String password) {
        return template.query(SQL_FIND_BY_LOGIN_AND_PASSWORD, userRowMapper, login, password).get(0);

    }

    @Override
    public User findUser(String login) {
        List<User> users = template.query(SQL_FIND_BY_LOGIN, userRowMapper, login);
        return users.get(0);
    }

    @Override
    public boolean findUSer(String login) {
        List<User> users = template.query(SQL_FIND_BY_LOGIN, userRowMapper, login);
        return !users.isEmpty();
    }
}
