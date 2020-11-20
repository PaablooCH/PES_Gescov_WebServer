package com.gescov.webserver.service;

import com.gescov.webserver.dao.classSession.ClassSessionDao;
import com.gescov.webserver.exception.IsNotAnAdministratorException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Subject> s = subjectService.findById(subjectID);
        Optional<User> u = userService.getUserById(teacherID);
        if(c.isEmpty()) throw new NotFoundException(Classroom.class, classroomID);
        if(s.isEmpty()) throw new NotFoundException(Subject.class, subjectID);
        if(u.isEmpty()) throw new NotFoundException(User.class, teacherID);
        return classSessionDao.insert(session);
    }

    public List<ClassSession> getAllSessions(){ return classSessionDao.findAll(); }

    public List<ClassSession> getSessionByClassroom(String id){ return classSessionDao.findAllByClassroomID(id); }

    public List<ClassSession> getSessionBySubject(String id){ return classSessionDao.findAllBySubjectID(id); }

    public List<ClassSession> getSessionByTeacher(String id){ return classSessionDao.findAllByTeacherID(id); }

    public List<ClassSession> getSessionByHour(String hour){ return classSessionDao.findAllByHour(hour); }

    public List<ClassSession> getSessionByDate(String date){ return classSessionDao.findAllByDate(date); }

    public void deleteClassSessionById(String usuID, String classSeID){
        Optional<ClassSession> cs = classSessionDao.findById(classSeID);
        if (cs.isEmpty()) throw new NotFoundException(ClassSession.class, classSeID);
        Optional<Classroom> c = classroomService.getClassroomById(cs.get().getClassroomID());
        if (c.isEmpty()) throw new NotFoundException(Classroom.class, cs.get().getClassroomID());
        Optional<School> s = schoolService.getSchoolById(c.get().getSchoolID());
        if (s.isEmpty()) throw new NotFoundException(School.class, c.get().getSchoolID());
        List<String> admins = s.get().getAdministratorsID();
        if(!admins.contains(usuID)) throw new IsNotAnAdministratorException(User.class, usuID);
        deleteAssignmentsOfASession(classSeID);
        classSessionDao.deleteById(classSeID);
    }

    private void deleteAssignmentsOfASession(String classSeID) {
        List<Assignment> as = assignmentService.getAssignmentByClassSessionId(classSeID);
        for(Assignment ass : as){
            assignmentService.deleteAssignmentById(ass.getId());
        }
    }

    public void deleteClassSession(String id){
        deleteAssignmentsOfASession(id);
        classSessionDao.deleteById(id);
    }

    public int getNumCol(String classSessionID) {
        Optional<ClassSession> classSession = classSessionDao.findById(classSessionID);
        if(classSession.isEmpty()) throw new NotFoundException(ClassSession.class, classSessionID);
        Optional<Classroom> classroom = classroomService.getClassroomById(classSession.get().getClassroomID());
        if(classroom.isEmpty()) throw new NotFoundException(Classroom.class, classSession.get().getClassroomID());
        return classroom.get().getNumCols();
    }

    public int getNumRow(String classSessionID) {
        Optional<ClassSession> classSession = classSessionDao.findById(classSessionID);
        if(classSession.isEmpty()) throw new NotFoundException(ClassSession.class, classSessionID);
        Optional<Classroom> classroom = classroomService.getClassroomById(classSession.get().getClassroomID());
        if(classroom.isEmpty()) throw new NotFoundException(Classroom.class, classSession.get().getClassroomID());
        return classroom.get().getNumRows();
    }

}
