package com.gescov.webserver.dao.subject;

import com.gescov.webserver.dao.school.SchoolDao;
import com.gescov.webserver.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subjectMongo")
public class SubjectDaoImpl implements SubjectDaoCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SubjectDaoImpl(MongoTemplate mongoTemplate, SchoolDao schoolDao) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Subject> selectAllBySchoolID(String schoolID){
        Query q = new Query();
        q.addCriteria(Criteria.where("schoolID").is(schoolID));
        return mongoTemplate.find(q, Subject.class);
    }

}

