package com.gescov.webserver.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class UserCustomizedMongoRepositoryImpl<T, ID> implements UserCustomizedMongoRepository<T, ID> {

    private MongoTemplate mongoTemplate;

    @Autowired
    public UserCustomizedMongoRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
