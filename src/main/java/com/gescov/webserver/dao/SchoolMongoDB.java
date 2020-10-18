package com.gescov.webserver.dao;

import com.gescov.webserver.model.School;
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

@Repository("schoolMongo")
public class SchoolMongoDB implements SchoolDao {

    @Qualifier("mongoClient")
    @Autowired
    private MongoClient client;
    private MongoCollection<School> schoolCollection;

    @PostConstruct
    void init() {
        schoolCollection = client.getDatabase("Gescov").getCollection("schools", School.class);
    }

    @Override
    public int insertSchool(School school) {
        schoolCollection.insertOne(school);
        return 1;
    }

    @Override
    public List<School> selectAllSchools() {
        List<School> allSchools = new ArrayList<>();
        FindIterable<School> result = schoolCollection.find();
        for(School s : result){
            allSchools.add(new School(s.getId(), s.getName(), s.getState()));
        }
        return allSchools;
    }

    @Override
    public Optional<School> selectSchoolById(ObjectId id) {
        School s = schoolCollection.find(eq("_id",id)).first();
        return Optional.ofNullable(s);
    }

    @Override
    public int deleteSchoolById(ObjectId id) {
        schoolCollection.findOneAndDelete(eq("_id", id));
        return 1;
    }

    @Override
    public int updateSchoolById(ObjectId id, School newSchool) {
        schoolCollection.findOneAndUpdate(eq("_id", id), set("name", newSchool.getName()));
        return 1;
    }

}


