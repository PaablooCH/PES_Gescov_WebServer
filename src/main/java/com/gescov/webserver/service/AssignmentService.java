package com.gescov.webserver.service;

import com.gescov.webserver.dao.assignment.AssignmentDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.PlaceOutOfIndexException;
import com.gescov.webserver.model.Assignment;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

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

    public List<Pair<Assignment, String>> getAssignmentsByClassroom(String classroomID) {
        List<Assignment> ass = assignmentDao.findByClassroom(classroomID);
        return getPairs(ass);
    }

    public List<Assignment> getAssignmentByClassSessionId(String classSessionID) {
        return assignmentDao.findAllByClassSessionID(classSessionID);
    }

    public List<Pair<Assignment, String>> getAssignmentByClassroomDate(String classroomID, String date) {
        List<Assignment> ass = assignmentDao.findByClassroomDate(classroomID, date);
        return getPairs(ass);
    }

    public List<Pair<Assignment, String>> getAssignmentByClassroomDateHour(String classroomID, String date, String hour) {
        List<Assignment> ass = assignmentDao.findByClassroomDateHour(classroomID, date, hour);
        return getPairs(ass);
    }

    private List<Pair<Assignment, String>> getPairs(List<Assignment> ass) {
        List<String> nameSts = new ArrayList<>();
        for (Assignment a : ass){
            User u = userService.getUserById(a.getStudentID());
            nameSts.add(u.getName());
        }
        List<Pair<Assignment, String>> aux = new ArrayList<>();
        for(int i = 0; i < ass.size(); i++) aux.add(Pair.of(ass.get(i), nameSts.get(i)));
        return aux;
    }

    public void deleteAssignmentById(String assID){
        assignmentDao.deleteById(assID);
    }

    public void transmitContagion(String studentID) {
        List<Assignment> ass = assignmentDao.findAllByStudentID(studentID);
        for (Assignment assignment : ass) {
            if (DAYS.between(classSessionService.getDateBySession(assignment.getClassSessionID()), LocalDate.now()) <= 2)
                checkPossibleContagion(assignment);
        }
    }

    private void checkPossibleContagion(Assignment as) {
        List<Assignment> ass = assignmentDao.findAllByClassSessionID(as.getClassSessionID());
        for (Assignment a : ass){
            if (a.getPosRow() == as.getPosRow() + 1 && a.getPosCol() == as.getPosCol() + 1) userService.infect(a.getStudentID());
            else if (a.getPosRow() == as.getPosRow() + 1 && a.getPosCol() == as.getPosCol()) userService.infect(a.getStudentID());
            else if (a.getPosRow() == as.getPosRow() + 1 && a.getPosCol() == as.getPosCol() - 1) userService.infect(a.getStudentID());
            else if (a.getPosRow() == as.getPosRow() && a.getPosCol() == as.getPosCol() + 1) userService.infect(a.getStudentID());
            else if (a.getPosRow() == as.getPosRow() - 1 && a.getPosCol() == as.getPosCol() + 1) userService.infect(a.getStudentID());
            else if(a.getPosRow() == as.getPosRow() && a.getPosCol() == as.getPosCol() - 1) userService.infect(a.getStudentID());
            else if (a.getPosRow() == as.getPosRow() - 1 && a.getPosCol() == as.getPosCol()) userService.infect(a.getStudentID());
            else if (a.getPosRow() == as.getPosRow() - 1 && a.getPosCol() == as.getPosCol() - 1) userService.infect(a.getStudentID());
        }
    }
}
