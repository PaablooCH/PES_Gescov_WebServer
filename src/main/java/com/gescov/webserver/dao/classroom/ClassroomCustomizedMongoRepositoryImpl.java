package com.gescov.webserver.dao.classroom;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.School;
import com.gescov.webserver.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class ClassroomCustomizedMongoRepositoryImpl<T, ID> implements ClassroomCustomizedMongoRepository<T, ID> {

    private final MongoTemplate mongoTemplate;
    private final SchoolService schoolService;

    @Autowired
    public ClassroomCustomizedMongoRepositoryImpl(MongoTemplate mongoTemplate, SchoolService schoolService) {
        this.mongoTemplate = mongoTemplate;
        this.schoolService = schoolService;
    }

    @Override
    public List<Classroom> selectSchoolClassrooms(String schoolName) {
        School school = schoolService.getSchoolByName(schoolName);
        Query q = new Query();
        q.addCriteria(Criteria.where("schoolID").is(school.getId()));
        return mongoTemplate.find(q, Classroom.class);
    }

}
