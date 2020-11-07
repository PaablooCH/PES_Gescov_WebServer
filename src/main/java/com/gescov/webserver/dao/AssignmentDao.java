package com.gescov.webserver.dao;

import com.gescov.webserver.model.Assignment;
import com.gescov.webserver.model.School;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentDao extends MongoRepository<Assignment, String>, AssignmentDaoCustom{

    /*List<Assignment> findByClassroomDate(String idClassroom, String date, String hour);

    List<Assignment> findByClassroom(String nameClass);

    List<Assignment> findByClassId(String idClassroom);*/
}
