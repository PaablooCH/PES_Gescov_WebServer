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
        classroomCollection.insertOne(classroom);
        return 1; //to do
    }

    @Override
    public List<Classroom> selectAllClassrooms() {
        List<Classroom> allClasses = new ArrayList<>();
        FindIterable<Classroom> result = classroomCollection.find();
        for (Classroom cr : result) {
            allClasses.add(new Classroom(cr.getId(), cr.getName(), cr.getCapacity(), cr.getCreator()));
        }
        return allClasses;
    }

    @Override
    public Classroom selectClassroomById(ObjectId id) {
        Classroom cr = classroomCollection.find(eq("_id", id)).first(); //first?
        if (cr == null) throw new NotFoundException("Classroom with 'id' " + id + " not found!");
        return cr;
    }

    @Override
    public int deleteClassroomById(ObjectId id) {
        Classroom classroomMaybe = selectClassroomById(id);
        classroomCollection.deleteOne(eq("_id", id));
        return 1;
    }

    @Override
    public int updateClassroomById(ObjectId id, Classroom update) { //only updates capacity field
        classroomCollection.findOneAndUpdate(eq("_id", id), set("capacity", update.getCapacity()));
        return 1;
    }

}
