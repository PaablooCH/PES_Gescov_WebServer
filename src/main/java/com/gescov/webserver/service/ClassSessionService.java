package com.gescov.webserver.service;

import com.gescov.webserver.dao.ClassSessionDao;
import com.gescov.webserver.model.ClassSession;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassSessionService {
    private final ClassSessionDao sessionDao;

    @Autowired
    public ClassSessionService(@Qualifier("classSessionMongo") ClassSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    public int addSession(ClassSession session){ return sessionDao.addSession(session); }

    public List<ClassSession> getAllSessions(){
        return sessionDao.selectAllSessions();
    }

    public List<ClassSession> getSessionByClassroom(String name){ return sessionDao.selectSessionsByClassroom(name); }

    public List<ClassSession> getSessionBySubject(String name){ return sessionDao.selectSessionsBySubject(name); }

    public List<ClassSession> getSessionByTeacher(String name){ return sessionDao.selectSessionsByTeacher(name); }

    public List<ClassSession> getSessionByHour(String hour){ return sessionDao.selectSessionsByHour(hour); }

    public List<ClassSession> getSessionByDate(String date){ return sessionDao.selectSessionsByDate(date); }

    public int deleteSession(ObjectId id){
        return sessionDao.deleteSession(id);
    }

    public int updateSubject(ObjectId id, ClassSession subject){
        return sessionDao.updateSession(id, subject);
    }
}
