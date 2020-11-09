package com.gescov.webserver.dao.classroom;

import com.gescov.webserver.model.Classroom;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ClassroomDaoCustom<T, ID> {

    List<Classroom> selectSchoolClassrooms(String schoolName);

}
