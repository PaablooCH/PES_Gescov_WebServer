package com.gescov.webserver.dao.assignment;

import com.gescov.webserver.model.Assignment;

import java.util.List;

public interface AssignmentDaoCustom {

    List<Assignment> findByClassroom(String classroomID);

    List<Assignment> findByClassroomDate(String classroomID, String date);

    List<Assignment> findByClassroomDateHour(String classroomID, String date, String hour);

}
