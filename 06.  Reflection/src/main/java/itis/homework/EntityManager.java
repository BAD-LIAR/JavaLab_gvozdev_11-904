package itis.homework;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 02.11.2020
 * 10. Reflection
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class EntityManager {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // createTable("account", User.class);
    public <T> void createTable(String tableName, Class<T> entityClass) {
        Field[] fields = entityClass.getDeclaredFields();

        String sqlInsert = "create table \"" + tableName + "\"( ";

        for (Field field : fields) {
            String tableType = chengType(field);
            sqlInsert += field.getName() + " " + tableType + ", ";

        }
        sqlInsert = sqlInsert.substring(0, sqlInsert.length() - 2);
        sqlInsert += ");";
        jdbcTemplate.update(sqlInsert);
        // сгенерировать CREATE TABLE на основе класса
        // create table account ( id integer, firstName varchar(255), ...))
    }

    public void save(String tableName, Object entity) {
        Class<?> classOfEntity = entity.getClass();
        Field[] fields = classOfEntity.getDeclaredFields();
        String sqlSave = "insert into \"" + tableName + "\"(";
        for (Field field : fields) {

            sqlSave += field.getName() + ", ";
        }
        sqlSave = sqlSave.substring(0, sqlSave.length() - 2);

        sqlSave += ") values (";

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String tableType = chengType(field);
                if (tableType.equals("varchar")) {
                    sqlSave += "\'" + field.get(entity) + "\' , ";
                } else {
                    sqlSave += field.get(entity) + " , ";
                }
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        }
        sqlSave = sqlSave.substring(0, sqlSave.length() - 2);
        sqlSave += ");";

        jdbcTemplate.update(sqlSave);
        // сканируем его поля
        // сканируем значения этих полей
        // генерируем insert into
    }

    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        T obj = null;

        String sql = "select * from " + tableName + " where id = " + idValue.toString() + ";";
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            obj = resultType.newInstance();

            while (resultSet.next()) {
                Field[] fields = resultType.getDeclaredFields();
                for (Field field : fields) {
                    Object object = resultSet.getObject(field.getName());
                    field.setAccessible(true);
                    field.set(obj, object);
                }

            }


        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }


        // сгенеририровать select
        return obj;
    }

    private String chengType(Field field) {
        String type = field.getType().getSimpleName();
        String tableType = "";
        switch (type) {
            case "Long":
            case "long":
                tableType = "bigint";
                break;
            case "Integer":
            case "int":
                tableType = "integer";
                break;
            case "short":
                tableType = "smallint";
                break;
            case "double":
            case "Double":
                tableType = "double precision";
                break;
            case "String":
                tableType = "varchar";
                break;
            case "boolean":
            case "Boolean":
                tableType = "boolean";
                break;
            default:
                tableType = "varchar";
                break;
        }
        return tableType;
    }
}
