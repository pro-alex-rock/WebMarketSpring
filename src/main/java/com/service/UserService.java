package com.service;

import com.dao.UserDao;
import com.model.Credentials;
import com.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserDao userDao;
    private final SecurityService securityService;

    public UserService(UserDao userDao, SecurityService securityService) {
        this.userDao = userDao;
        this.securityService = securityService;
    }

    public Optional<User> getByName(String login) {
        return userDao.getByName(login);
    }

    public Optional<User> select(int id) {
        return userDao.select(id);
    }

    public List<User> selectAll() {
        return userDao.selectAll();
    }

    public void create(User user) {
        userDao.create(user);
    }

    public void delete(int id) {
        userDao.delete(id);
    }
}
