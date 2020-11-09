package com.gescov.webserver.dao.user;

import com.gescov.webserver.model.User;
import com.mongodb.client.FindIterable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends MongoRepository<User, String>, UserCustomizedMongoRepository<User, String> {

    FindIterable<User> findBySchoolsIDContaining(String schoolID);

}
