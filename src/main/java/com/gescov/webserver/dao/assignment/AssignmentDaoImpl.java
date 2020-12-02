package com.gescov.webserver.dao.assignment;

import com.gescov.webserver.dao.classSession.ClassSessionDao;
import com.gescov.webserver.exception.ClassroomDateException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Assignment;
import com.gescov.webserver.model.ClassSession;
import com.gescov.webserver.model.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository("assignmentMongo")
public class AssignmentDaoImpl<T, ID> implements AssignmentDaoCustom<T, ID> {

    private final MongoTemplate mongoTemplate;
    private final ClassSessionDao classSessionDao;

    @Autowired
    public AssignmentDaoImpl(MongoTemplate mongoTemplate, ClassSessionDao classSessionDao) {
        this.mongoTemplate = mongoTemplate;
        this.classSessionDao = classSessionDao;
    }

    @Override
    public List<Assignment> findByClassroom(String classroomID) {
        List<ClassSession> classSessions = classSessionDao.findAllByClassroomID(classroomID);
        if (classSessions.isEmpty()) throw new NotFoundException(Classroom.class, classroomID);
        Query q = getClassSessionIDs(classSessions);
        return mongoTemplate.find(q, Assignment.class);
    }

    @Override
    public List<Assignment> findByClassroomDate(String classroomID, String date) {
        List<ClassSession> classSessions = classSessionDao.findAllByClassroomIDAndDate(classroomID, LocalDate.parse(date));
        if (classSessions.isEmpty()) throw new ClassroomDateException(Classroom.class, classroomID, date);
        Query q = getClassSessionIDs(classSessions);
        return mongoTemplate.find(q, Assignment.class);
    }

    private Query getClassSessionIDs(List<ClassSession> classSessions) {
        List<String> classSessionID = new ArrayList<>();
        Query q = new Query();
        for (ClassSession cs : classSessions) classSessionID.add(cs.getId());
        q.addCriteria(Criteria.where("classSessionID").in(classSessionID));
        return q;
    }

    @Override
    public List<Assignment> findByClassroomDateHour(String classroomID, String date, String hour) {
        List<ClassSession> classSessions = classSessionDao.findAllByClassroomIDAndDateAndHour(classroomID, LocalDate.parse(date), LocalTime.parse(hour));
        if (classSessions.isEmpty()) throw new ClassroomDateException(Classroom.class, classroomID, date);
        Query q = getClassSessionIDs(classSessions);
        return mongoTemplate.find(q, Assignment.class);
    }
}
