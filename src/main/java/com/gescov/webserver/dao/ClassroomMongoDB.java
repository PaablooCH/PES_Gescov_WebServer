package com.gescov.webserver.dao;

import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Classroom;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

@Repository("classroomMongo")
public class ClassroomMongoDB implements ClassroomDao {

    @Qualifier("mongoClient")
    @Autowired
    private MongoClient client;
    private MongoCollection<Classroom> classroomCollection;

    @PostConstruct
    void init() {
        classroomCollection = client.getDatabase("Gescov").getCollection("classrooms", Classroom.class);
    }

    @Override
    public int insertClassroom(Classroom classroom) {
        if (classroomCollection.countDocuments(eq("name", classroom.getName())) == 0) {
            classroomCollection.insertOne(classroom);
            return 1;
        }
        throw new AlreadyExistsException("Classroom with 'name' " + classroom.getName() + " already exists!");
    }

    @Override
    public List<Classroom> selectAllClassrooms() {
        List<Classroom> allClasses = new ArrayList<>();
        FindIterable<Classroom> result = classroomCollection.find();
        for (Classroom cr : result) allClasses.add(cr);
        return allClasses;
    }

    @Override
    public Classroom selectClassroomById(ObjectId id) {
        FindIterable<Classroom> result = classroomCollection.find(eq("_id", id));
        Classroom cl = null;
        for (Classroom cr : result) cl = cr;
        if (cl == null) throw new NotFoundException("Classroom with 'id' " + id + " not found!");
        return cl;
    }

    @Override
    public Pair<Integer, Integer> selectClassroomDistributionById(ObjectId id) {
        Classroom cr = selectClassroomById(id);
        return Pair.of(cr.getNumRows(), cr.getNumCols());
    }

    @Override
    public int deleteClassroomById(ObjectId id) {
        classroomCollection.findOneAndDelete(eq("_id", id));
        return 1;
    }

    @Override
    public int updateClassroomNameById(ObjectId id, String name) {
        classroomCollection.findOneAndUpdate(eq("_id", id), set("name", name));
        return 1;
    }

    @Override
    public int updateClassroomCapacityById(ObjectId id, int capacity) {
        classroomCollection.findOneAndUpdate(eq("_id", id), set("capacity", capacity));
        return 1;
    }

    @Override
    public int updateClassroomNumRowsById(ObjectId id, int numRows) {
        classroomCollection.findOneAndUpdate(eq("_id", id), set("numRows", numRows));
        return 1;
    }

    @Override
    public int updateClassroomNumColsById(ObjectId id, int numCols) {
        classroomCollection.findOneAndUpdate(eq("_id", id), set("numCols", numCols));
        return 1;
    }

}
