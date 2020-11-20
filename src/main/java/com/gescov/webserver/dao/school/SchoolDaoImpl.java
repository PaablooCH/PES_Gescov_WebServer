package com.gescov.webserver.dao.school;

import com.gescov.webserver.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository("schoolMongo")
public class SchoolDaoImpl<T,ID> implements SchoolDaoCustom<T,ID> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SchoolDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean existsByIdAndAdministratorsIDIn(String schoolID, String adminID) {
        Query q = new Query();
        q.addCriteria(Criteria.where("id").is(schoolID));
        q.addCriteria(Criteria.where("administratorsID").is(adminID));
        return !mongoTemplate.find(q, School.class).isEmpty();
    }
}
