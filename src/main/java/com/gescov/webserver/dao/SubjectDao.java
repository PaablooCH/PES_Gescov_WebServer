package com.gescov.webserver.dao;

import com.gescov.webserver.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDao extends MongoRepository<Subject, String>, SubjectDaoCustom {

    List<Subject> findAllBySchool(String school);

    List<Subject> findAllByName(String name);

}
