package com.gescov.webserver.service;

import com.gescov.webserver.dao.user.UserDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public User addUser(User user) {
        return userDao.insert(user);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userDao.findById(id);
    }

    public List<String> getSchoolsByUser(String id) {
        Optional<User> us = getUserById(id);
        if (us.isEmpty()) throw new NotFoundException("User with 'id' " + id + " not found!");
        return us.get().getSchoolsID();
    }

    public boolean existsUser(String userID) {
        return userDao.existsById(userID);
    }

    public void addSchool(String id, String update) {
        Optional<User> u = userDao.findById(id);
        if (u.isEmpty()) throw new NotFoundException("User with 'id' " + id + " not found!");
        u.get().addSchool(update);
        userDao.save(u.get());
    }

}
