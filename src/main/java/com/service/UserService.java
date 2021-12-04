package com.service;

import com.dao.DaoResource;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements DefaultService<User> {
    private final DaoResource<User> jdbcUserDao;

    public UserService(DaoResource<User> jdbcUserDao) {
        this.jdbcUserDao = jdbcUserDao;
    }

    public Optional<User> getByName(String login) {
        return jdbcUserDao.getByName(login);
    }

    @Override
    public Optional<User> selectOne(int id) {
        return jdbcUserDao.selectOne(id);
    }

    @Override
    public List<User> selectAll() {
        return jdbcUserDao.selectAll();
    }

    @Override
    public void create(User user) {
        jdbcUserDao.create(user);
    }

    @Override
    public void updateOne(User user) {

    }

    @Override
    public void delete(int id) {
        jdbcUserDao.delete(id);
    }
}
