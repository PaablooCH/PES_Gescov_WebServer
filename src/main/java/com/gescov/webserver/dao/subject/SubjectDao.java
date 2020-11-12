package com.gescov.webserver.dao.subject;

import com.gescov.webserver.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDao extends MongoRepository<Subject, String>, SubjectDaoCustom<Subject, String> {

    List<Subject> findAllByName(String name);

    List<Subject> findAllBySchoolID(String id);

}
