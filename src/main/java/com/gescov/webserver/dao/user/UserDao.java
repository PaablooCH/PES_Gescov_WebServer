package com.gescov.webserver.dao.user;

import com.gescov.webserver.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends MongoRepository<User, String>, UserDaoCustom<User, String> {

    List<User> findAllByIdIn(List<String> usersID);

}
