package com.gescov.webserver.dao;

import com.gescov.webserver.model.Subject;
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
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


@Repository("subjectMongo")
public class SubjectMongoDB implements SubjectDao {

    @Qualifier("mongoClient")
    @Autowired
    private MongoClient client;
    private MongoCollection<Subject> subjectCollection;

    @PostConstruct
    void init() {
        subjectCollection = client.getDatabase("Gescov").getCollection("subject", Subject.class);
    }

    @Override
    public int insertSubject(Subject subject) {
        subjectCollection.insertOne(subject);
        return 1;
    }

    @Override
    public List<Subject> selectAllSubjects() {
        List<Subject> allSubjects = new ArrayList<>();
        FindIterable<Subject> result = subjectCollection.find();
        for(Subject s : result){
            allSubjects.add(new Subject(s.getId(), s.getName(), s.getSchool()));
        }
        return allSubjects;
    }

    @Override
    public Optional<Subject> selectSubjectsById(ObjectId id) {
        Subject s = subjectCollection.find(eq("_id",id)).first();
        return Optional.ofNullable(s);
    }

    @Override
    public int deleteSubject(ObjectId id) {
        subjectCollection.findOneAndDelete(eq("_id", id));
        return 1;
    }

    @Override
    public int updateSubject(ObjectId id, Subject subject) {
        subjectCollection.findOneAndUpdate(eq("_id", id), set("name", subject.getName()));
        return 1;
    }
}
