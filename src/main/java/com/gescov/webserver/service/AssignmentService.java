package com.gescov.webserver.service;

import com.gescov.webserver.dao.AssignmentDao;
import com.gescov.webserver.model.Assignment;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentDao assignmentDao;

    @Autowired
    public AssignmentService(@Qualifier("assignmentMongo")AssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public int addAssignment(Assignment assignment) { return assignmentDao.insertAssignment(assignment); }

    public int updateAssignment(ObjectId id, int posRow, int posCol) {
        assignmentDao.updateAssignmentRow(id, posRow);
        assignmentDao.updateAssignmentCol(id, posCol);
        return 1;
    }

    public List<Assignment> getAllAssignment() { return assignmentDao.selectAllAssignment(); }

    public List<Assignment> getAssignmentBySchool(String nameCen) {
        return assignmentDao.selectAssignmentBySchool(nameCen);
    }

    public List<Assignment> getAssignmentByClassSession(ObjectId id) {
        return assignmentDao.selectAssignmentBySession(id);
    }
}
