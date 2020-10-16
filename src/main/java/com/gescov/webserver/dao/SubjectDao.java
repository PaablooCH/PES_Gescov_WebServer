package com.gescov.webserver.dao;

import com.gescov.webserver.model.Subject;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface SubjectDao {

    int insertSubject(Subject subject);

    List<Subject> selectAllSubjects();

    Optional<Subject> selectSubjectsById(ObjectId id);

    int deleteSubject(ObjectId id);

    int updateSubject(ObjectId id, Subject subject);
}
