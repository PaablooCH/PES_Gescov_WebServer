package com.gescov.webserver.dao.classSession;

import com.gescov.webserver.dao.classroom.ClassroomDao;
import com.gescov.webserver.dao.subject.SubjectDao;
import com.gescov.webserver.dao.user.UserDao;
import com.gescov.webserver.model.ClassSession;
import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.Subject;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("classSessionMongo")
public class ClassSessionDaoImpl<T,ID> implements ClassSessionDaoCustom<T,ID> {

    private final MongoTemplate mongoTemplate;
    private final SubjectDao subjectDao;
    private final UserDao userDao;
    private final ClassroomDao classroomDao;

    @Autowired
    public ClassSessionDaoImpl(MongoTemplate mongoTemplate, SubjectDao subjectDao, UserDao userDao, ClassroomDao classroomDao) {
        this.mongoTemplate = mongoTemplate;
        this.subjectDao = subjectDao;
        this.userDao = userDao;
        this.classroomDao = classroomDao;
    }

}

