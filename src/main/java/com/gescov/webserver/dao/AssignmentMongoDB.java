package com.gescov.webserver.dao;

import com.gescov.webserver.model.Assignment;
import com.gescov.webserver.model.Classroom;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class AssignmentMongoDB implements AssignmentDao{

    @Qualifier("mongoClient")
    @Autowired
    private MongoClient client;
    private MongoCollection<Assignment> assignmentCollection;

    public AssignmentMongoDB(MongoCollection<Assignment> assignmentCollection) {
        this.assignmentCollection = assignmentCollection;
    }

    @Override
    public int insertAssignment(Assignment assignment) {
        FindIterable<Assignment> result = assignmentCollection.find(eq("nameSt",assignment.getNameSt()));
        boolean insert = true;
        for (Assignment as : result) {
            if (as.getClassSession().equals(assignment.getClassSession())) {
                insert = false;
                break;
            }
        }
        if (insert) {
            assignmentCollection.insertOne(assignment);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Assignment> selectAllAssignment() {
        return null;
    }

    @Override
    public int deleteClassroomById(ObjectId id) {
        return 0;
    }

    @Override
    public int updateAssignmentByID(ObjectId id, Classroom classroom) {
        return 0;
    }
}
