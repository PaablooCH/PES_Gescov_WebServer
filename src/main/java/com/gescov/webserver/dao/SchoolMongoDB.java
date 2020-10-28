package com.gescov.webserver.dao;

import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Classroom;
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
        if (schoolCollection.countDocuments(eq("name", school.getName())) == 0) {
            schoolCollection.insertOne(school);
            return 1;
        }
        throw new AlreadyExistsException("School with 'name' " + school.getName() + " already exists!");
    }

    @Override
    public List<School> selectAllSchools() {
        List<School> allSchools = new ArrayList<>();
        FindIterable<School> result = schoolCollection.find();
        for (School s : result) allSchools.add(s);
        return allSchools;
    }

    @Override
    public School selectSchoolById(ObjectId id) {
        FindIterable<School> result = schoolCollection.find(eq("_id", id));
        School s = null;
        for (School sc : result) s = sc;
        if (s == null) throw new NotFoundException("School with 'id' " + id + " not found!");
        return s;
    }

    @Override
    public int deleteSchoolById(ObjectId id) {
        schoolCollection.findOneAndDelete(eq("_id", id));
        return 1;
    }

    @Override
    public int updateSchoolNameById(ObjectId id, String name) {
        schoolCollection.findOneAndUpdate(eq("_id", id), set("name", name));
        return 1;
    }

    @Override
    public int updateSchoolStateById(ObjectId id, String state) {
        schoolCollection.findOneAndUpdate(eq("_id", id), set("state", state));
        return 1;
    }

}


