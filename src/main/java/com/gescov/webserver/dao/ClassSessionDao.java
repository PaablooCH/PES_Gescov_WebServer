package com.gescov.webserver.dao;

import com.gescov.webserver.model.ClassSession;
import org.bson.types.ObjectId;

import java.util.List;

public interface ClassSessionDao {

    int addSession(ClassSession session);

    List<ClassSession> selectAllSessions();

    List<ClassSession> selectSessionsByClassroom(String variable);

    List<ClassSession> selectSessionsBySubject(String variable);

    List<ClassSession> selectSessionsByStudent(String variable);

    List<ClassSession> selectSessionsByHour(String variable);

    List<ClassSession> selectSessionsByDate(String variable);

    int deleteSession(ObjectId id);

    int updateSession(ObjectId id, ClassSession session);
}
