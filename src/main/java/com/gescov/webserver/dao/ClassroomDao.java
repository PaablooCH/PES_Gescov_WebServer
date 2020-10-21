package com.gescov.webserver.dao;

import com.gescov.webserver.model.Classroom;
import org.bson.types.ObjectId;
import java.util.List;

public interface ClassroomDao {

    int insertClassroom(Classroom classroom);

    List<Classroom> selectAllClassrooms();

    Classroom selectClassroomById(ObjectId id);

    int deleteClassroomById(ObjectId id);

    int updateClassroomById(ObjectId id, Classroom classroom);

}

