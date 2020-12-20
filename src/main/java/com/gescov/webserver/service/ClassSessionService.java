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
        Optional<Classroom> c = classroomService.getClassroomById(classroomID);
        Subject s = subjectService.getSubjectById(subjectID);
        if (c.isEmpty()) throw new NotFoundException(Classroom.class, classroomID);
        if (s.getTeachersID().contains(teacherID)) throw new NotFoundException(User.class, teacherID);
        String classSchool = c.get().getSchoolID();
        String subjectSchool = s.getSchoolID();
        if (!classSchool.equals(subjectSchool)) throw new NotEqualsException(School.class, classSchool, subjectSchool);
        if (classSessionDao.existsByClassroomIDAndDateIsAndHourIs(classroomID, session.getDate(), session.getHour()))
            throw new ClassroomInUseException(classroomID, session.getDate(), session.getHour());
        return classSessionDao.insert(session);
    }

    public List<ClassSession> getAllSessions() { return classSessionDao.findAll(); }

    public List<ClassSession> getSessionByClassroom(String id) { return classSessionDao.findAllByClassroomID(id); }

    public List<ClassSession> getSessionBySubject(String id) { return classSessionDao.findAllBySubjectID(id); }

    public List<ClassSession> getSessionByTeacher(String id) { return classSessionDao.findAllByTeacherID(id); }

    public List<ClassSession> getSessionByHour(String hour) { return classSessionDao.findAllByHour(LocalTime.parse(hour)); }

    public List<ClassSession> getSessionByDate(String date) { return classSessionDao.findAllByDate(LocalDate.parse(date)); }

    public void deleteClassSessionById(String usuID, String classSeID){
        Optional<Classroom> c = getClassroomByCSID(classSeID);
        School s = schoolService.getSchoolByID(c.get().getSchoolID());
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
        Optional<Classroom> classroom = getClassroomByCSID(classSessionID);
        return classroom.get().getNumCols();
    }

    public int getNumRow(String classSessionID) {
        Optional<Classroom> classroom = getClassroomByCSID(classSessionID);
        return classroom.get().getNumRows();
    }

    private Optional<Classroom> getClassroomByCSID(String classSessionID) {
        Optional<ClassSession> classSession = classSessionDao.findById(classSessionID);
        if (classSession.isEmpty()) throw new NotFoundException(ClassSession.class, classSessionID);
        Optional<Classroom> classroom = classroomService.getClassroomById(classSession.get().getClassroomID());
        if (classroom.isEmpty()) throw new NotFoundException(Classroom.class, classSession.get().getClassroomID());
        return classroom;
    }

    public LocalDate getDateBySession(String classSessionID) {
        Optional<ClassSession> classSession = classSessionDao.findById(classSessionID);
        if (classSession.isEmpty()) throw new NotFoundException(ClassSession.class, classSessionID);
        return classSession.get().getDate();
    }

}
