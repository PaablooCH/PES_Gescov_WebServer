package com.gescov.webserver.dao.assignment;

import com.gescov.webserver.model.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentDao extends MongoRepository<Assignment, String>, AssignmentDaoCustom<Assignment, String>{

    /*List<Assignment> findByClassroomDate(String idClassroom, String date, String hour);

    List<Assignment> findByClassroom(String nameClass);

    List<Assignment> findByClassId(String idClassroom);*/

    List<Assignment> findAllByClassSessionID(String classSessionID);

}
