package com.gescov.webserver.service;

import com.gescov.webserver.dao.classroom.ClassroomDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomDao classroomDao;

    @Autowired
    public ClassroomService(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }


    public Classroom addClassroom(Classroom classroom) {
        return classroomDao.insert(classroom);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomDao.findAll();
    }

    public List<Classroom> getClassroomsBySchool(String schoolName) {
        return classroomDao.selectSchoolClassrooms(schoolName);
    }

    public Optional<Classroom> getClassroomById(String id) {
        return classroomDao.findById(id);
    }


    public Pair<Integer, Integer> getClassroomDistributionById(String id) {
        Classroom c = classroomDao.findClassroomById(id);
        if (c == null) throw new NotFoundException("Classroom with 'id' " + id + " not found!");
        return Pair.of(c.getNumRows(), c.getNumCols());
    }

    public void deleteClassroom(String id) {
        classroomDao.deleteById(id);
    }

    public void updateClassroomCapacity(String id, int capacity) {
        Optional<Classroom> s = classroomDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException("Classroom with 'id' " + id + " not found!");
        s.get().setCapacity(capacity);
        classroomDao.save(s.get());
    }

    public void updateClassroomName(String id, String name) {
        Optional<Classroom> s = classroomDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException("Classroom with 'id' " + id + " not found!");
        s.get().setName(name);
        classroomDao.save(s.get());
    }

    public void updateClassroomNumRows(String id, int numRows) {
        Optional<Classroom> s = classroomDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException("Classroom with 'id' " + id + " not found!");
        s.get().setNumRows(numRows);
        classroomDao.save(s.get());
    }

    public void updateClassroomNumCols(String id, int numCols) {
        Optional<Classroom> s = classroomDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException("Classroom with 'id' " + id + " not found!");
        s.get().setNumCols(numCols);
        classroomDao.save(s.get());
    }

}
