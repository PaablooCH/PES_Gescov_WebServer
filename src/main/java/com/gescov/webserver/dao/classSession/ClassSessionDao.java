package com.gescov.webserver.dao.classSession;

import com.gescov.webserver.model.ClassSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ClassSessionDao extends MongoRepository <ClassSession, String> {

    List<ClassSession> findAllByHour(LocalTime hour);

    List<ClassSession> findAllByDate(LocalDate date);

    List<ClassSession> findAllByClassroomID(String id);

    List<ClassSession> findAllBySubjectID(String id);

    List<ClassSession> findAllByTeacherID(String id);

    List<ClassSession> findAllByClassroomIDAndDate(String classroomID, LocalDate date);

    List<ClassSession> findAllByClassroomIDAndDateAndHour(String classroomID, LocalDate date, LocalTime hour);

}
