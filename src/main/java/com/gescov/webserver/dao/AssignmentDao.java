package com.gescov.webserver.dao;

import com.gescov.webserver.model.Assignment;
import org.bson.types.ObjectId;

import java.util.List;

public interface AssignmentDao {

    int insertAssignment(Assignment assignment);

    List<Assignment> selectAllAssignment();

    int deleteAssignment(ObjectId id);

    int updateAssignmentRow(ObjectId id, int posRow);

    int updateAssignmentCol(ObjectId id, int posCol);

    List<Assignment> selectAssignmentBySchool(String nameCen);
}
