package com.gescov.webserver.dao;

import com.gescov.webserver.model.Classroom;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

public interface ClassroomDao {

    int insertClassroom(Classroom classroom);

    List<Classroom> selectAllClassrooms();

    Optional<Classroom> selectClassroomById(ObjectId id);

    int deleteClassroomById(ObjectId id);

    int updateClassroomById(ObjectId id, Classroom classroom);

}

