package com.gescov.webserver.service;

import com.gescov.webserver.dao.classSession.ClassSessionDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.ClassSession;
import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.Subject;
import com.gescov.webserver.model.User;
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

    public void deleteSession(String id){
        classSessionDao.deleteById(id);
    }

    public int getNumCol(String classSessionID) {
        Optional<ClassSession> classSession = classSessionDao.findById(classSessionID);
        if(classSession.isEmpty()) throw new NotFoundException(ClassSession.class, classSessionID);
        return classroomService.getClassroomById(classSession.get().getClassroomID()).get().getNumCols();
    }

    public int getNumRow(String classSessionID) {
        Optional<ClassSession> classSession = classSessionDao.findById(classSessionID);
        if(classSession.isEmpty()) throw new NotFoundException(ClassSession.class, classSessionID);
        return classroomService.getClassroomById(classSession.get().getClassroomID()).get().getNumRows();
    }
/*
    public void updateSubject(String id, String subject){
        Optional<ClassSession> s = classSessionDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException("ClassSession with 'id'" + id + "not found!");
        s.get().setName(na);
        subjectDao.insert(s.get());
    }
 */
}
