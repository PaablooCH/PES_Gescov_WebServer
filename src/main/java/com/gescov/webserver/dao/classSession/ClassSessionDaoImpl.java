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


    public List<ClassSession> selectAllByClassroomId(String id){
        Optional<Classroom> classroom = classroomDao.findById(id);
        Query q = new Query();
        q.addCriteria(Criteria.where("classroomID").is(classroom.get().getId()));
        return mongoTemplate.find(q, ClassSession.class);
    }

    public List<ClassSession> selectAllBySubjectId(String id){
        Optional<Subject> subject = subjectDao.findById(id);
        Query q = new Query();
        q.addCriteria(Criteria.where("subjectID").is(subject.get().getId()));
        return mongoTemplate.find(q, ClassSession.class);
    }

    public List<ClassSession> selectAllByTeacherId(String id){
        Optional<User> user = userDao.findById(id);
        Query q = new Query();
        q.addCriteria(Criteria.where("teacherID").is(user.get().getId()));
        return mongoTemplate.find(q, ClassSession.class);
    }
}

