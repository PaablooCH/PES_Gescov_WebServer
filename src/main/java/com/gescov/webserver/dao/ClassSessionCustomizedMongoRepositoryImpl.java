package com.gescov.webserver.dao;
/*
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.ClassSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

@Repository("classSessionMongo")
public class ClassSessionCustomizedMongoRepositoryImpl<T,ID> implements ClassSessionCustomizedMongoRepository<T,ID>{

    @Qualifier("mongoClient")
    @Autowired
    private MongoClient client;
    private MongoCollection<ClassSession> sessionCollection;
    private static final String NOT_EXIST_ERROR = " does not exist";

    @PostConstruct
    void init() {
        sessionCollection = client.getDatabase("Gescov").getCollection("classSession",
                ClassSession.class);
    }

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
