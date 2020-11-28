package com.gescov.webserver.dao.assignment;

import com.gescov.webserver.model.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentDao extends MongoRepository<Assignment, String>, AssignmentDaoCustom<Assignment, String>{

    List<Assignment> findAllByClassSessionID(String classSessionID);

    List<Assignment> findAllByStudentID(String studentID);

}
