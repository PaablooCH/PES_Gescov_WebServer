package com.gescov.webserver.dao.subject;

import com.gescov.webserver.dao.school.SchoolDao;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("subjectMongo")
public class SubjectDaoImpl<T,ID> implements SubjectDaoCustom<T,ID> {

    private final MongoTemplate mongoTemplate;
    private final SchoolDao schoolDao;

    @Autowired
    public SubjectDaoImpl(MongoTemplate mongoTemplate, SchoolDao schoolDao) {
        this.mongoTemplate = mongoTemplate;
        this.schoolDao = schoolDao;
    }

    public List<Subject> selectAllBySchoolId (String id){
        Optional<School> school = schoolDao.findById(id);
        Query q = new Query();
        q.addCriteria(Criteria.where("schoolID").is(school.get().getId()));
        return mongoTemplate.find(q, Subject.class);
    }

    public List<Subject> selectAllBySchoolName (String schoolName){
        School school = schoolDao.findByName(schoolName);
        Query q = new Query();
        q.addCriteria(Criteria.where("schoolID").is(school.getId()));
        return mongoTemplate.find(q, Subject.class);
    }
}

