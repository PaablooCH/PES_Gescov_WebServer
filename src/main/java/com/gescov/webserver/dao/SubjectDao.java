package com.gescov.webserver.dao;

import com.gescov.webserver.model.Subject;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface SubjectDao {

    int insertSubject(Subject subject);

    List<Subject> selectAllSubjects();

    List<Subject> selectSubjectsByVariable(String variable);

    int deleteSubject(String name);

    int updateSubject(String name, Subject subject);

}
