package com.gescov.webserver.service;

import com.gescov.webserver.dao.assignment.AssignmentDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.PlaceOutOfIndexException;
import com.gescov.webserver.model.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    AssignmentDao assignmentDao;

    @Autowired
    ClassSessionService classSessionService;

    @Autowired
    UserService userService;


    public Assignment addAssignment(Assignment assignment) {
        int numRow = classSessionService.getNumRow(assignment.getClassSessionID());
        int numCol = classSessionService.getNumCol(assignment.getClassSessionID());
        if (assignment.getPosRow() > numRow || assignment.getPosCol() > numCol) throw new PlaceOutOfIndexException(numRow, numCol);
        userService.existsUser(assignment.getStudentID());
        return assignmentDao.insert(assignment);
    }

    public void updateAssignment(String id, int posRow, int posCol) {
        Optional<Assignment> ass = assignmentDao.findById(id);
        if (ass.isEmpty()) throw new NotFoundException(Assignment.class, id);
        ass.get().setPosCol(posCol);
        ass.get().setPosRow(posRow);
        assignmentDao.save(ass.get());
    }

    public List<Assignment> getAllAssignment() { return assignmentDao.findAll(); }

    public List<Assignment> getAssignmentsByClassroom(String classroomID) {
        return assignmentDao.findByClassroom(classroomID);
    }

    public List<Assignment> getAssignmentByClassSessionId(String classSessionID) {
        return assignmentDao.findAllByClassSessionID(classSessionID);
    }

    public List<Assignment> getAssignmentByClassroomDate(String classroomID, String date) {
        return assignmentDao.findByClassroomDate(classroomID, date);
    }

    public void deleteAssignmentById(String assID){
        assignmentDao.deleteById(assID);
    }
}
