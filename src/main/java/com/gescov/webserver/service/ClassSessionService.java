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
    private final ClassSessionDao classSessionDao;
    private final ClassroomService classroomService;
    private final SubjectService subjectService;
    private final UserService userService;

    @Autowired
    public ClassSessionService(ClassSessionDao classSessionDao, ClassroomService classroomService, SubjectService subjectService, UserService userService) {
        this.classSessionDao = classSessionDao;
        this.classroomService = classroomService;
        this.subjectService = subjectService;
        this.userService = userService;
    }

    public ClassSession addSession(ClassSession session){ return classSessionDao.insert(session); }

    public List<ClassSession> getAllSessions(){ return classSessionDao.findAll(); }

    public List<ClassSession> getSessionByClassroom(String name){ return classSessionDao.selectAllByClassroomId(name); }

    public List<ClassSession> getSessionBySubject(String name){ return classSessionDao.selectAllBySubjectId(name); }

    public List<ClassSession> getSessionByTeacher(String name){ return classSessionDao.selectAllByTeacherId(name); }

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
