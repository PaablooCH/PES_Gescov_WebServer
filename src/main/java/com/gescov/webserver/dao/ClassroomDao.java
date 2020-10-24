package com.gescov.webserver.dao;

import com.gescov.webserver.model.Classroom;
import org.bson.types.ObjectId;

import java.util.List;

public interface ClassroomDao {

    int insertClassroom(Classroom classroom);

    List<Classroom> selectAllClassrooms();

    Classroom selectClassroomById(ObjectId id);

    int deleteClassroomById(ObjectId id);

    int updateClassroomNameById(ObjectId id, String name);

    int updateClassroomCapacityById(ObjectId id, int capacity);

    int updateClassroomNumRowsById(ObjectId id, int numCols);

    int updateClassroomNumColsById(ObjectId id, int numCols);

}

