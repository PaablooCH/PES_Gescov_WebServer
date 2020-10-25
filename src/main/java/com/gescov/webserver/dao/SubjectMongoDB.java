package com.gescov.webserver.dao;

import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.Subject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

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
            else throw new AlreadyExistsException(subject.getName() + " already exists in " + subject.getSchool().getName());
        }
        else subjectCollection.insertOne(subject);
        return 1;
    }

    @Override
    public List<Subject> selectAllSubjects() {
        List<Subject> allSubjects = new ArrayList<>();
        FindIterable<Subject> result = subjectCollection.find();
        for(Subject s : result){
            allSubjects.add(s);
        }
        return allSubjects;
    }

    @Override
    public List<Subject> selectSubjectsBySchool(String school) {
        List<Subject> SchoolSubjects = new ArrayList<>();
        FindIterable<Subject> result = subjectCollection.find(eq("school.name",school));
        for (Subject s : result) {
            SchoolSubjects.add(s);
        }
        return SchoolSubjects;
    }

    @Override
    public List<Subject> selectSubjectsByName(String name) {
        List<Subject> NameSubjects = new ArrayList<>();
        FindIterable<Subject> result = subjectCollection.find(eq("name", name));
        for (Subject s : result) {
            NameSubjects.add(s);
        }
        return NameSubjects;
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
