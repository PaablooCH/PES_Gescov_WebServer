package com.gescov.webserver.dao.contagion;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.Contagion;
import com.gescov.webserver.model.User;
import com.gescov.webserver.service.UserService;
import com.mongodb.client.FindIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("contagionMongo")
public class ContagionDaoImpl<T, ID> implements ContagionDaoCustom<Contagion, String> {

    private final MongoTemplate mongoTemplate;
    private final UserService userService;

    @Autowired
    public ContagionDaoImpl(MongoTemplate mongoTemplate, UserService userService) {
        this.mongoTemplate = mongoTemplate;
        this.userService = userService;
    }

    @Override
    public List<Contagion> findInfectedBySchool(String schoolID) {
        FindIterable<User> infected = userService.getUserBySchool(schoolID);
        Query q = new Query();
        for (User in : infected) {
            q.addCriteria(Criteria.where("infectedID").is(in.getId()));
        }
        return mongoTemplate.find(q, Contagion.class);
    }
}