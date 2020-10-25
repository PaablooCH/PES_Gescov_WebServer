package com.gescov.webserver.dao;

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
import static com.mongodb.client.model.Updates.rename;
import static com.mongodb.client.model.Updates.set;

@Repository("classSessionMongo")
public class ClassSessionMongoDB implements ClassSessionDao{

    @Qualifier("mongoClient")
    @Autowired
    private MongoClient client;
    private MongoCollection<ClassSession> sessionCollection;

    @PostConstruct
    void init() {
        sessionCollection = client.getDatabase("Gescov").getCollection("classSession", ClassSession.class);
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
        List<ClassSession> ClassroomSessions = new ArrayList<>();
        FindIterable<ClassSession> result = sessionCollection.find(eq("classroom.name", name));
        if(result.cursor().hasNext()) {
            for (ClassSession cs : result) {
                ClassroomSessions.add(cs);
            }
            return ClassroomSessions;
        }
        else throw new NotFoundException("The session in a classroom named: " + name + " doesn't exists");
    }

    @Override
    public List<ClassSession> selectSessionsBySubject(String name) {
        List<ClassSession> SubjectSessions = new ArrayList<>();
        FindIterable<ClassSession> result = sessionCollection.find(eq("subject.name", name));
        if(result.cursor().hasNext()) {
            for (ClassSession cs : result) {
                SubjectSessions.add(cs);
            }
            return SubjectSessions;
        }
        else throw new NotFoundException("The session with a subject named: " + name + " doesn't exists");
    }

    @Override
    public List<ClassSession> selectSessionsByStudent(String name) {
        List<ClassSession> StudentSessions = new ArrayList<>();
        FindIterable<ClassSession> result = sessionCollection.find(eq("student", name));
        if(result.cursor().hasNext()) {
            for (ClassSession cs : result) {
                StudentSessions.add(cs);
            }
            return StudentSessions;
        }
        else throw new NotFoundException("The session with a student named :" + name + " doesn't exists");
    }

    @Override
    public List<ClassSession> selectSessionsByHour(String hour) {
        List<ClassSession> HourSessions = new ArrayList<>();
        FindIterable<ClassSession> result = sessionCollection.find(eq("hora", hour));
        if(result.cursor().hasNext()) {
            for (ClassSession cs : result) {
                HourSessions.add(cs);
            }
            return HourSessions;
        }
        else throw new NotFoundException("The session with the hour: " + hour + " doesn't exists");
    }

    @Override
    public List<ClassSession> selectSessionsByDate(String date) {
        List<ClassSession> DateSessions = new ArrayList<>();
        FindIterable<ClassSession> result = sessionCollection.find(eq("date", date));
        if(result.cursor().hasNext()) {
            for (ClassSession cs : result) {
                DateSessions.add(cs);
            }
            return DateSessions;
        }
        else throw new NotFoundException("The session with the date: " + date + " doesn't exists");
    }

    @Override
    public int deleteSession(ObjectId id) {
        sessionCollection.findOneAndDelete(eq("_id",id));
        return 1;
    }


    @Override
    public int updateSession(ObjectId id, ClassSession session) {
        sessionCollection.findOneAndUpdate(eq("_id", id),set("student", session.getStudent()));
        return 1;
    }
}
