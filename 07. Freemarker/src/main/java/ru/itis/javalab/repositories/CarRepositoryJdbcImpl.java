package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.javalab.models.Car;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CarRepositoryJdbcImpl implements CarRepository{

    //language=SQL
    private static final String SQL_SELECT = "select * from cars;";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from cars where id = ?";



    //language=SQL
    private static final String SQL_SAVE_USER = "insert into cars (model, user_id) VALUES (?, ?);";





    private DataSource dataSource;

    public CarRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        template = new JdbcTemplate(dataSource);
    }

//    private SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);

    private JdbcTemplate template;

    private RowMapper<Car> carRowMapper = (row, i) -> Car.builder()
            .model(row.getString("model"))
            .ownerId(row.getLong("user_id"))
            .id(row.getLong("id"))
            .build();


    @Override
    public List<Car> getAllCars() {
        return template.query(SQL_SELECT, carRowMapper);
    }

    @Override
    public Car findCar(Integer id) {
        return template.query(SQL_FIND_BY_ID, carRowMapper, id).get(0);
    }

    @Override
    public void saveCar(String model, Long ownerId) {
        template.update(SQL_SAVE_USER, model, ownerId);
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Car entity) {

    }

    @Override
    public void update(Car entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Car entity) {

    }
}
