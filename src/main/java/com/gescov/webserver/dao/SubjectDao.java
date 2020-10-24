package com.gescov.webserver.dao;

import com.gescov.webserver.model.School;
import com.gescov.webserver.model.Subject;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface SubjectDao {

    int insertSubject(Subject subject);

    List<Subject> selectAllSubjects();

    List<Subject> selectSubjectsBySchool(String school);

    List<Subject> selectSubjectsByName(String name);

    int deleteSubject(String name);

    int updateSubject(String name, Subject subject);

}
