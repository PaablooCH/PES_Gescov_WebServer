package com.gescov.webserver.dao;

import com.gescov.webserver.exception.AlreadyExistsException;
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
    public int insertAssignment(Assignment assignment) { //Eficiente Funciona// @Pablo CH
        FindIterable<Assignment> result = assignmentCollection.find(eq("classSession", assignment.getClassSession()));
        boolean nameRepeated = false;
        boolean posNotValid = false;
        for (Assignment as : result) {
            if (as.getNameSt().equals(assignment.getNameSt())) {
                nameRepeated = true;
                break;
            }
            if (as.getPosCol() == assignment.getPosCol() && as.getPosRow() == assignment.getPosRow()) {
                posNotValid = true;
                break;
            }
        }

        if (nameRepeated) {
            throw new AlreadyExistsException("The person with 'name' " + assignment.getNameSt() +
                    " is already in the classSession");
        }
        else if (posNotValid) {
            throw new AlreadyExistsException("The position with 'row' " + assignment.getPosRow() + " and 'col' " +
                    assignment.getPosCol() + " is already occupied");
        }

        assignmentCollection.insertOne(assignment);
        return 1;
    }

    @Override
    public List<Assignment> selectAllAssignment() { //Eficiente Funciona// @Pablo CH
        List<Assignment> allAssignment = new ArrayList<>();
        FindIterable<Assignment> result = assignmentCollection.find();
        for (Assignment as : result) {
            allAssignment.add(as);
        }
        return allAssignment;
    }

    @Override
    public int deleteAssignment(ObjectId id) { //Eficiente Funciona// @Pablo CH
        assignmentCollection.deleteOne(eq("id", id));
        return 1;
    }

    @Override
    public int updateAssignmentRow(ObjectId id, int posRow) { //Eficiente Funciona// @Pablo CH
        assignmentCollection.findOneAndUpdate(eq("_id", id), set("posRow", posRow));
        return 1;
    }

    @Override
    public int updateAssignmentCol(ObjectId id, int posCol) { //Eficiente Funciona// @Pablo CH
        assignmentCollection.findOneAndUpdate(eq("_id", id), set("posCol", posCol));
        return 1;
    }
}