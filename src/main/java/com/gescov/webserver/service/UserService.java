package com.gescov.webserver.service;

import com.gescov.webserver.dao.ClassroomDao;
import com.gescov.webserver.dao.UserDao;
import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("userMongo")UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    public User getUserById(ObjectId id) {
        return userDao.selectUserById(id);
    }

    public List<School> getUserSchools(ObjectId id) { return userDao.getUserSchools(id); }
}
