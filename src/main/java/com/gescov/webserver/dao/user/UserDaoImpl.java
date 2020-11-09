package com.gescov.webserver.dao.user;

import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserDaoImpl<T, ID> implements UserDaoCustom<T, ID> {

    private MongoTemplate mongoTemplate;

    @Autowired
    public UserDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> findAllBySchoolsID(String schoolID) {
        Query q = new Query();
        q.addCriteria(Criteria.where("schoolsID").is(schoolID));
        return mongoTemplate.find(q, User.class);
    }
}