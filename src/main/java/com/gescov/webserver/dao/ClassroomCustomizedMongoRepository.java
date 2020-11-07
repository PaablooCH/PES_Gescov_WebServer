package com.gescov.webserver.dao;

import com.gescov.webserver.model.Classroom;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ClassroomCustomizedMongoRepository<T, ID> {

    List<Classroom> selectSchoolClassrooms(String schoolName);

    Pair<Integer, Integer> selectClassroomDistributionById(String id);

}
