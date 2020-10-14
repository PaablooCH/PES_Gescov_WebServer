package com.gescov.webserver.service;

import com.gescov.webserver.dao.ClassroomDao;
import com.gescov.webserver.model.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Classroom> getClassroomById(UUID id) {
        return classroomDao.selectClassroomById(id);
    }

    public int deleteClassroom(UUID id) {
        return classroomDao.deleteClassroomById(id);
    }

    public int updateClassroom(UUID id, Classroom newClassroom) {
        return classroomDao.updateClassroomById(id, newClassroom);
    }
}
