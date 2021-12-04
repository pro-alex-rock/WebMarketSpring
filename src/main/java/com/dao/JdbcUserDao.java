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
    }

    @Override
    public Optional<User> selectOne(int id) {
        if (id <= 0) {
            logger.info("Inserted incorrect id.");
            throw new RuntimeException("Inserted incorrect id.");
        }
        return jdbcTemplate.query("SELECT username, password, sole FROM users WHERE id=?"
                , new BeanPropertyRowMapper<>(User.class), new Object[]{id}).stream().findAny();
    }

    @Override
    public List<User> selectAll() {
        return jdbcTemplate.query("SELECT id, username, password, sole FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void create(User user) {
        jdbcTemplate.update("INSERT INTO users (username, password, sole) VALUES (?, ?, ?)",
                user.getName(), user.getPassword(), user.getSole());
        logger.info("The user {} created.", user);
    }


    public void updateOne(User user) {
        jdbcTemplate.update("UPDATE users SET username = ?, password = ?, sole = ? WHERE username = ?",
                user.getName(), user.getPassword(), user.getSole(), user.getName());
        logger.info("The user {} updated", user.getName());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
        logger.info("The user with id: {} deleted", id);
    }
}
