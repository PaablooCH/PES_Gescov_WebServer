package com.gescov.webserver.dao;

import com.gescov.webserver.model.ClassSession;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassSessionDao extends MongoRepository <ClassSession, String> {

    List<ClassSession> findAllByClassroom_Id(String variable);

    List<ClassSession> findAllBySubject_Id(String variable);

    List<ClassSession> findAllByTeacher_Id(String variable);

    List<ClassSession> findAllByHour(String variable);

    List<ClassSession> findAllByDate(String variable);

}
