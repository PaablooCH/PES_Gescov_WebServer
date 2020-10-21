package com.gescov.webserver.service;

import com.gescov.webserver.dao.ClassroomDao;
import com.gescov.webserver.exception.EntityNotFoundException;
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

    public int updateClassroom(ObjectId id, Classroom newClassroom) {
        return classroomDao.updateClassroomById(id, newClassroom);
    }

}
