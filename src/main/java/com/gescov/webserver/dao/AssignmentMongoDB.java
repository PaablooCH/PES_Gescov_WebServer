package com.gescov.webserver.dao;

import com.gescov.webserver.model.Assignment;
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

@Repository("assignmentMongo")
public class AssignmentMongoDB implements AssignmentDao{

    @Qualifier("mongoClient")
    @Autowired
    private MongoClient client;
    private MongoCollection<Assignment> assignmentCollection;

    @PostConstruct
    void init() {
        assignmentCollection = client.getDatabase("Gescov").getCollection("assignments", Assignment.class);
    }

    @Override
    public int insertAssignment(Assignment assignment) { //comprobar lugares ya ocupados en la clase
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
        List<Assignment> allAssignment = new ArrayList<>();
        FindIterable<Assignment> result = assignmentCollection.find();
        for (Assignment as : result) {
            allAssignment.add(as);
        }
        return allAssignment;
    }

    @Override
    public int deleteAssignment(ObjectId id) {
        assignmentCollection.deleteOne(eq("id", id));
        return 1;
    }

    @Override
    public int updateAssignmentRow(ObjectId id, int posRow) {
        assignmentCollection.findOneAndUpdate(eq("_id", id), set("posRow", posRow));
        return 1;
    }

    @Override
    public int updateAssignmentCol(ObjectId id, int posCol) {
        assignmentCollection.findOneAndUpdate(eq("_id", id), set("posCol", posCol));
        return 1;
    }
}
