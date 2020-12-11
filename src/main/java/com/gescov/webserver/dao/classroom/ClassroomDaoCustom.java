package com.gescov.webserver.dao.classroom;

import com.gescov.webserver.model.Classroom;

import java.util.List;

public interface ClassroomDaoCustom {

    List<Classroom> selectClassroomsBySchool(String schoolName);

}
