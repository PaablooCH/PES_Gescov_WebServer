package com.gescov.webserver.service;

import com.gescov.webserver.dao.classSession.ClassSessionDao;
import com.gescov.webserver.exception.ClassroomInUseException;
import com.gescov.webserver.exception.IsNotAnAdministratorException;
import com.gescov.webserver.exception.NotEqualsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClassSessionService {

    @Autowired
    ClassSessionDao classSessionDao;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    AssignmentService assignmentService;

    public ClassSession addSession(ClassSession session){
        String classroomID = session.getClassroomID();
        String subjectID = session.getSubjectID();
        String teacherID = session.getTeacherID();
        Classroom c = classroomService.getClassroomById(classroomID);
        Subject s = subjectService.getSubjectById(subjectID);
        if (s.getTeachersID().contains(teacherID)) throw new NotFoundException(User.class, teacherID);
        String classSchool = c.getSchoolID();
        String subjectSchool = s.getSchoolID();
        if (!classSchool.equals(subjectSchool)) throw new NotEqualsException(School.class, classSchool, subjectSchool);
        if (session.getHour().isAfter(session.getFinishHour())){
            LocalTime z = session.getHour();
            session.setHour(session.getFinishHour());
            session.setFinishHour(z);
        }
        if (isClassroomNotFree(classroomID, session.getDate(), session.getHour(), session.getFinishHour()))
            throw new ClassroomInUseException(classroomID, session.getDate(), session.getHour());
        return classSessionDao.insert(session);
    }

    private boolean isClassroomNotFree(String classroomID, LocalDate date, LocalTime hour, LocalTime finishHour) {
        List<ClassSession> classSessions = classSessionDao.findAllByClassroomIDAndDate(classroomID, date);
        for (ClassSession cs : classSessions){
            if (cs.getHour().isAfter(hour) && cs.getHour().isBefore(finishHour)) return true;
            else if (cs.getFinishHour().isAfter(hour) && cs.getFinishHour().isBefore(finishHour)) return true;
            else if (cs.getHour().equals(hour) && cs.getFinishHour().equals(finishHour)) return true;
        }
        return false;
    }

    public List<ClassSession> getAllSessions() { return classSessionDao.findAll(); }

    public List<ClassSession> getSessionByClassroom(String id) { return classSessionDao.findAllByClassroomID(id); }

    public List<ClassSession> getSessionBySubject(String id) { return classSessionDao.findAllBySubjectID(id); }

    public List<ClassSession> getSessionByTeacher(String id) { return classSessionDao.findAllByTeacherID(id); }

    public List<ClassSession> getSessionByHour(String hour) { return classSessionDao.findAllByHour(LocalTime.parse(hour)); }

    public List<ClassSession> getSessionByDate(String date) { return classSessionDao.findAllByDate(LocalDate.parse(date)); }

    public ClassSession getClassSessionByID(String id){
        Optional<ClassSession> classSession = classSessionDao.findById(id);
        if (classSession.isEmpty()) throw new NotFoundException(ClassSession.class, id);
        return classSession.get();
    }
    public void deleteClassSessionById(String usuID, String classSeID){
        Classroom c = getClassroomByCSID(classSeID);
        School s = schoolService.getSchoolByID(c.getSchoolID());
        List<String> admins = s.getAdministratorsID();
        if (!admins.contains(usuID)) throw new IsNotAnAdministratorException(User.class, usuID, s.getId());
        deleteAssignmentsOfASession(classSeID);
        classSessionDao.deleteById(classSeID);
    }

    private void deleteAssignmentsOfASession(String classSeID) {
        List<Assignment> as = assignmentService.getAssignmentByClassSessionId(classSeID);
        for (Assignment ass : as){
            assignmentService.deleteAssignmentById(ass.getId());
        }
    }

    public void deleteClassSession(String id) {
        deleteAssignmentsOfASession(id);
        classSessionDao.deleteById(id);
    }

    public int getNumCol(String classSessionID) {
        Classroom classroom = getClassroomByCSID(classSessionID);
        return classroom.getNumCols();
    }

    public int getNumRow(String classSessionID) {
        Classroom classroom = getClassroomByCSID(classSessionID);
        return classroom.getNumRows();
    }

    private Classroom getClassroomByCSID(String classSessionID) {
        ClassSession classSession = getClassSessionByID(classSessionID);
        Classroom classroom = classroomService.getClassroomById(classSession.getClassroomID());
        return classroom;
    }

    public LocalDate getDateBySession(String classSessionID) {
        ClassSession classSession = getClassSessionByID(classSessionID);
        return classSession.getDate();
    }

}
