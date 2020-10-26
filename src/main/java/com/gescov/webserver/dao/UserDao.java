package com.gescov.webserver.dao;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserDao {

    int insertUser(User user);

    List<User> selectAllUsers();

    User selectUserById(ObjectId id);

    List<School> getUserSchools(ObjectId id);
}
