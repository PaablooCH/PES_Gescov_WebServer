package com.gescov.webserver.service;

import com.gescov.webserver.dao.ClassroomDao;
import com.gescov.webserver.model.Classroom;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    private final ClassroomDao classroomDao;

    @Autowired
    public ClassroomService(@Qualifier("classroomMongo")ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    public int addClassroom(Classroom classroom) {
        return classroomDao.insertClassroom(classroom);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomDao.selectAllClassrooms();
    }

    public Classroom getClassroomById(ObjectId id) {
        return classroomDao.selectClassroomById(id);
    }

    public int deleteClassroom(ObjectId id) {
        return classroomDao.deleteClassroomById(id);
    }

    public int updateClassroomCapacity(ObjectId id, int capacity) {
        return classroomDao.updateClassroomCapacityById(id, capacity);
    }

    public int updateClassroomName(ObjectId id, String name) {
        return classroomDao.updateClassroomNameById(id, name);
    }

    public int updateClassroomNumRows(ObjectId id, int numRows) {
        return classroomDao.updateClassroomNumRowsById(id, numRows);
    }

    public int updateClassroomNumCols(ObjectId id, int numCols) {
        return classroomDao.updateClassroomNumColsById(id, numCols);
    }

}
