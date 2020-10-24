package com.gescov.webserver.dao;

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
    public List<ClassSession> selectSessionsByClassroom(String variable) {
        return null;
    }

    @Override
    public List<ClassSession> selectSessionsBySubject(String variable) {
        return null;
    }

    @Override
    public List<ClassSession> selectSessionsByStudent(String variable) {
        return null;
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
