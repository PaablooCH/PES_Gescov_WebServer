package com.gescov.webserver.dao.classSession;

import com.gescov.webserver.model.ClassSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassSessionDao extends MongoRepository <ClassSession, String> , ClassSessionDaoCustom<ClassSession, String> {

    List<ClassSession> findAllByHour(String hour);

    List<ClassSession> findAllByDate(String date);

    List<ClassSession> findAllByClassroomID(String variable);

    List<ClassSession> findAllBySubjectID(String variable);

    List<ClassSession> findAllByTeacherID(String variable);

    List<ClassSession> findAllByClassroomIDAndDate(String classroomID, String date);

}
