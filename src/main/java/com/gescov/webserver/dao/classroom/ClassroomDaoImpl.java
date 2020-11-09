package com.gescov.webserver.dao.classroom;

import com.gescov.webserver.dao.school.SchoolDao;
import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("classroomMongo")
public class ClassroomDaoImpl<T, ID> implements ClassroomDaoCustom<T, ID> {

    private final MongoTemplate mongoTemplate;
    private final SchoolDao schoolDao;

    @Autowired
    public ClassroomDaoImpl(MongoTemplate mongoTemplate, SchoolDao schoolDao) {
        this.mongoTemplate = mongoTemplate;
        this.schoolDao = schoolDao;
    }

    @Override
    public List<Classroom> selectClassroomsBySchool(String schoolName) {
        School school = schoolDao.findByName(schoolName);
        Query q = new Query();
        q.addCriteria(Criteria.where("schoolID").is(school.getId()));
        return mongoTemplate.find(q, Classroom.class);
    }

}
