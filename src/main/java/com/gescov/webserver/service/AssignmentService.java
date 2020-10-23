package com.gescov.webserver.service;

import com.gescov.webserver.dao.AssignmentDao;
import com.gescov.webserver.model.Assignment;

public class AssignmentService {

    private final AssignmentDao assignmentDao;

    public AssignmentService(AssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public int addAssignment(Assignment assignment) { return assignmentDao.insertAssignment(assignment); }
}
