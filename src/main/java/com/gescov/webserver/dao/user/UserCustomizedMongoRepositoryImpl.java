package com.gescov.webserver.dao.user;

import com.gescov.webserver.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class UserCustomizedMongoRepositoryImpl<T, ID> implements UserCustomizedMongoRepository<T, ID> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<School> getUserSchools(String id) {
        return null;
    }
}
