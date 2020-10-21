package com.gescov.webserver.dao;

import com.gescov.webserver.model.School;
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
        if(subjectCollection.countDocuments(eq("name", subject.getName()))!=0) {
            if(subjectCollection.countDocuments(eq("school", subject.getSchool()))==0){
                subjectCollection.insertOne(subject);
                return 1;
            }
            else return 0;
        }
        else subjectCollection.insertOne(subject);
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
    public List<Subject> selectSubjectsByVariable(String variable) {
        List<Subject> VariableSubjects = new ArrayList<>();
        FindIterable<Subject> result = subjectCollection.find(eq("school", variable));
        if (result.cursor().hasNext()) {
            for (Subject s : result) {
                VariableSubjects.add(new Subject(s.getId(), s.getName(), s.getSchool()));
            }
        }
        else {
            result = subjectCollection.find(eq("name", variable));
            for (Subject s : result) {
                VariableSubjects.add(new Subject(s.getId(), s.getName(), s.getSchool()));
            }
        }
        return VariableSubjects;
    }


    @Override
    public int deleteSubject(String name) {
        subjectCollection.findOneAndDelete(eq("name", name));
        return 1;
    }

    @Override
    public int updateSubject(String name, Subject subject) {
        subjectCollection.findOneAndUpdate(eq("name", name), set("name", subject.getName()));
        return 1;
    }
}
