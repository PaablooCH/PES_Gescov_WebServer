package com.gescov.webserver.dao.assignment;

import com.gescov.webserver.model.Assignment;

import java.util.List;

public interface AssignmentDaoCustom<T, ID> {

    List<Assignment> findByClassroom(String classroomID);

}
