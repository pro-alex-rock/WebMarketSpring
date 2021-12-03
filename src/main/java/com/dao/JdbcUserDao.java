package com.dao;


import com.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserDao implements DaoResource<User>{
    private static final Logger logger = LoggerFactory.getLogger(JdbcUserDao.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> getByName(String name) {
        if (name == null) {
            logger.info("Inserted incorrect name.");
            throw new RuntimeException("Inserted incorrect name.");
        }
        return jdbcTemplate.query("SELECT id, username, password, sole FROM users WHERE username = ?"
                , new BeanPropertyRowMapper<>(User.class), new Object[]{name}).stream().findAny();


        /*try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id, username, password, sole FROM users WHERE username = ?")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            //resultSet.next();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSole(resultSet.getString("sole"));
                logger.info("Success get user {} from db", user);
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            logger.info("Couldn`t get user from db because connection failed" + e);
            throw new RuntimeException("Couldn`t get user from db because connection failed", e);
        }*/
    }

    @Override
    public Optional<User> selectOne(int id) {
        if (id <= 0) {
            logger.info("Inserted incorrect id.");
            throw new RuntimeException("Inserted incorrect id.");
        }
        return jdbcTemplate.query("SELECT username, password, sole FROM users WHERE id=?"
                , new BeanPropertyRowMapper<>(User.class), new Object[]{id}).stream().findAny();


        /*if (id <= 0) {
            logger.info("Inserted incorrect id.");
            throw new RuntimeException("Inserted incorrect id.");
        }
        User user = new User();
        try(PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT username, password, sole FROM users WHERE id=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user.setId(id);
            user.setName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setSole(resultSet.getString("sole"));
            resultSet.close();
        } catch (SQLException e) {
            logger.info("Couldn`t select user with id {} from db" + e, id);
            throw new RuntimeException("Couldn`t select user with id " + id + " from db", e);
        }
        logger.info("The user with id: {} selected", id);
        return user;*/
    }

    @Override
    public List<User> selectAll() {
        return jdbcTemplate.query("SELECT id, username, password, sole FROM users", new BeanPropertyRowMapper<>(User.class));

        /*List<User> users = new ArrayList<>();
        try(PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT id, username, password, sole FROM users");
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSole(resultSet.getString("sole"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.info("Couldn`t select users from db" + e);
            throw new RuntimeException("Couldn`t select users from db", e);
        }
        logger.info("All users selected.");
        return users;*/
    }

    @Override
    public void create(User user) {
        jdbcTemplate.update("INSERT INTO users (username, password, sole) VALUES (?, ?, ?)",
                user.getName(), user.getPassword(), user.getSole());
        logger.info("The user {} created.", user);


        /*try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, sole) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSole());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Couldn`t create new user." + e);
            throw new RuntimeException("Couldn`t create new user.", e);
        }*/
    }


    public void updateOne(User user) {
        jdbcTemplate.update("UPDATE users SET username = ?, password = ?, sole = ? WHERE username = ?",
                user.getName(), user.getPassword(), user.getSole(), user.getName());
        logger.info("The user {} updated", user.getName());

        /*try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ?, password = ?, sole = ? WHERE name = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSole());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Couldn`t update user: {}" + e, user);
            throw new RuntimeException("Couldn`t update user: " + user, e);
        }*/
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
        logger.info("The user with id: {} deleted", id);

        /*try(PreparedStatement statement = dataSource.getConnection().prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Couldn`t delete user by id: {}" + e, id);
            throw new RuntimeException("Couldn`t delete user by id: " + id, e);
        }*/
    }
}
