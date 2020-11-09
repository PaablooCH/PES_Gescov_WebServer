package com.gescov.webserver.dao.classSession;

import com.gescov.webserver.dao.classroom.ClassroomDao;
import com.gescov.webserver.dao.subject.SubjectDao;
import com.gescov.webserver.dao.user.UserDao;
import com.gescov.webserver.model.*;
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




    /*
    @Override
    public int addSession(ClassSession session) {
        sessionCollection.insertOne(session);
        return 1;
    }

    @Override
    public List<ClassSession> selectAllSessions() {
        List<ClassSession> allSessions = new ArrayList<>();
        FindIterable<ClassSession> result = sessionCollection.find();
        for(ClassSession cs : result){
            allSessions.add(cs);
        }
        return allSessions;
    }

    @Override
    public List<ClassSession> selectSessionsByClassroom(String name) {
        List<ClassSession> classroomSessions = new ArrayList<>();
        FindIterable<ClassSession> result = sessionCollection.find(eq("classroom.name", name));
        if(result.cursor().hasNext()) {
            for (ClassSession cs : result) {
                classroomSessions.add(cs);
            }
            return classroomSessions;
        }
        else throw new NotFoundException("The session in a classroom named: " + name + NOT_EXIST_ERROR);
    }

    @Override
    public List<ClassSession> selectSessionsBySubject(String name) {
        List<ClassSession> subjectSessions = new ArrayList<>();
        FindIterable<ClassSession> result = sessionCollection.find(eq("subject.name", name));
        if(result.cursor().hasNext()) {
            for (ClassSession cs : result) {
                subjectSessions.add(cs);
            }
            return subjectSessions;
        }
        else throw new NotFoundException("The session with a subject named: " + name + NOT_EXIST_ERROR);
    }

    @Override
    public List<ClassSession> selectSessionsByTeacher(String name) {
        List<ClassSession> teacherSessions = new ArrayList<>();
        FindIterable<ClassSession> result = sessionCollection.find(eq("teacher.name", name));
        if(result.cursor().hasNext()) {
            for (ClassSession cs : result) {
                teacherSessions.add(cs);
            }
            return teacherSessions;
        }
        else throw new NotFoundException("The session with a teacher named :" + name + NOT_EXIST_ERROR);
    }

    @Override
    public List<ClassSession> selectSessionsByHour(String hour) {
        List<ClassSession> hourSessions = new ArrayList<>();
        FindIterable<ClassSession> result = sessionCollection.find(eq("hora", hour));
        if(result.cursor().hasNext()) {
            for (ClassSession cs : result) {
                hourSessions.add(cs);
            }
            return hourSessions;
        }
        else throw new NotFoundException("The session with the hour: " + hour + NOT_EXIST_ERROR);
    }

    @Override
    public List<ClassSession> selectSessionsByDate(String date) {
        List<ClassSession> dateSessions = new ArrayList<>();
        FindIterable<ClassSession> result = sessionCollection.find(eq("date", date));
        if(result.cursor().hasNext()) {
            for (ClassSession cs : result) {
                dateSessions.add(cs);
            }
            return dateSessions;
        }
        else throw new NotFoundException("The session with the date: " + date + NOT_EXIST_ERROR);
    }

    @Override
    public int deleteSession(ObjectId id) {
        sessionCollection.findOneAndDelete(eq("_id",id));
        return 1;
    }


    @Override
    public int updateSession(ObjectId id, ClassSession session) {
        sessionCollection.findOneAndUpdate(eq("_id", id),set("student", session.getTeacher()));
        return 1;
    }
}
 */
