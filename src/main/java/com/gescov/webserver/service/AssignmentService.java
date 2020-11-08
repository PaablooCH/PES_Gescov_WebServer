package com.gescov.webserver.service;

import com.gescov.webserver.dao.assignment.AssignmentDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentDao assignmentDao;

    @Autowired
    public AssignmentService(AssignmentDao assignmentDao) { this.assignmentDao = assignmentDao; }

    public Assignment addAssignment(Assignment assignment) { return assignmentDao.insert(assignment); }

    public void updateAssignment(String id, int posRow, int posCol) {
        Optional<Assignment> ass = assignmentDao.findById(id);
        if (ass.isEmpty()) throw new NotFoundException("Assignment with 'id'" + id + "not found!");
        ass.get().setPosCol(posCol);
        ass.get().setPosRow(posRow);
        assignmentDao.save(ass.get());
    }

    public List<Assignment> getAllAssignment() { return assignmentDao.findAll(); }

    /*public List<Assignment> getAssignmentByClassroom(String nameClass) {
        return assignmentDao.findByClassroom(nameClass);
    }

    public List<Assignment> getAssignmentByClassId(String idClassroom) {
        return assignmentDao.findByClassId(idClassroom);
    }

    public List<Assignment> getAssignmentByClassroomDate(String idClassroom, String date, String hour) {
        return assignmentDao.findByClassroomDate(idClassroom, date, hour);
    }*/
}
