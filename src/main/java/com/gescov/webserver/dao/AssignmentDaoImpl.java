package com.gescov.webserver.dao;
/*
import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.model.Assignment;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class AssignmentDaoImpl implements AssignmentDaoCustom{

    private final MongoTemplate mongoTemplate;

    public AssignmentDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public int insertAssignment(Assignment assignment) { //Eficiente Funciona// @Pablo CH
        FindIterable<Assignment> result = assignmentCollection.find(eq("classSession", assignment.getClassSession()));
        boolean nameRepeated = false;
        boolean posNotValid = false;
        for (Assignment as : result) {
            if (as.getStudent().getName().equals(assignment.getStudent().getName())) {
                nameRepeated = true;
            }
            if (as.getPosCol() == assignment.getPosCol() && as.getPosRow() == assignment.getPosRow()) {
                posNotValid = true;
            }
        }

        if(nameRepeated && posNotValid){
            throw new AlreadyExistsException("The person with 'name' " + assignment.getStudent().getName() +
                    " is already in the classSession and the position with 'row' " + assignment.getPosRow() + " and 'col' "
                    +  assignment.getPosCol() + " is already occupied");
        }
        else if (nameRepeated) {
            throw new AlreadyExistsException("The person with 'name' " + assignment.getStudent().getName() +
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
    public List<Assignment> findByClassroomDate(String idClassroom, String date, String hour) {
        List<Assignment> allAssignment = new ArrayList<>();
        FindIterable<Assignment> result = assignmentCollection.find(eq("classSession.classroom._id", idClassroom));
        for (Assignment as : result) {
            if (as.getClassSession().getDate().equals(date) && as.getClassSession().getHora().equals(hour))
                allAssignment.add(as);
        }
        return allAssignment;
    }

    @Override
    public List<Assignment> findByClassroom(String nameClass) {
        List<Assignment> allAssignment = new ArrayList<>();
        FindIterable<Assignment> result = assignmentCollection.find(eq("classSession.classroom.name", nomClass));
        for (Assignment as : result) {
            allAssignment.add(as);
        }
        return allAssignment;
    }

    @Override
    public List<Assignment> findByClassId(String idClassroom) {
        List<Assignment> allAssignment = new ArrayList<>();
        FindIterable<Assignment> result = assignmentCollection.find(eq("classSession._id", id));
        for (Assignment as : result) {
            allAssignment.add(as);
        }
        return allAssignment;
    }
}*/
