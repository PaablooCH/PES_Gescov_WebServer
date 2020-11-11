package com.gescov.webserver.dao.TracingTest;

import com.gescov.webserver.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository("tracingTestMongo")
public class TracingTestDaoImpl<T,ID> implements TracingTestDaoCustom<T,ID> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TracingTestDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
