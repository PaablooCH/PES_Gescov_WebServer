package com.gescov.webserver.service;

import com.gescov.webserver.dao.ClassSessionDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.ClassSession;
import com.gescov.webserver.model.Subject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassSessionService {
    private final ClassSessionDao classSessionDao;

    @Autowired
    public ClassSessionService(ClassSessionDao classSessionDao) {
        this.classSessionDao = classSessionDao;
    }

    public ClassSession addSession(ClassSession session){ return classSessionDao.insert(session); }

    public List<ClassSession> getAllSessions(){ return classSessionDao.findAll(); }

    public List<ClassSession> getSessionByClassroom(String name){ return classSessionDao.findAllByClassroom_Id(name); }

    public List<ClassSession> getSessionBySubject(String name){ return classSessionDao.findAllBySubject_Id(name); }

    public List<ClassSession> getSessionByTeacher(String name){ return classSessionDao.findAllByTeacher_Id(name); }

    public List<ClassSession> getSessionByHour(String hour){ return classSessionDao.findAllByHour(hour); }

    public List<ClassSession> getSessionByDate(String date){ return classSessionDao.findAllByDate(date); }

    public void deleteSession(String id){
        classSessionDao.deleteById(id);
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
