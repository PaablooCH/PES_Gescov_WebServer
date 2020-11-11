package com.gescov.webserver.service;

import com.gescov.webserver.dao.classroom.ClassroomDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.WrongCapacityException;
import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    ClassroomDao classroomDao;

    @Autowired
    SchoolService schoolService;


    public Classroom addClassroom(Classroom classroom) {
        if ((classroom.getNumRows() * classroom.getNumCols()) != classroom.getCapacity()) throw new WrongCapacityException(Classroom.class);
        String schoolID = classroom.getSchoolID();
        Optional<School> s = schoolService.getSchoolById(schoolID);
        if (s.isEmpty()) throw new NotFoundException(School.class, schoolID);
        return classroomDao.insert(classroom);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomDao.findAll();
    }

    public List<Classroom> getClassroomsBySchool(String schoolName) {
        return classroomDao.selectClassroomsBySchool(schoolName);
    }
    public List<Classroom> getClassroomsBySchoolID(String id) {
        return classroomDao.findAllBySchoolID(id);
    }

    public Optional<Classroom> getClassroomById(String id) {
        return classroomDao.findById(id);
    }

    public Pair<Integer, Integer> getClassroomDistributionById(String id) {
        Classroom c = classroomDao.findClassroomById(id);
        if (c == null) throw new NotFoundException(Classroom.class, id);
        return Pair.of(c.getNumRows(), c.getNumCols());
    }

    public void deleteClassroom(String id) {
        classroomDao.deleteById(id);
    }

    public void updateClassroomCapacity(String id, int capacity) {
        Optional<Classroom> s = classroomDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(Classroom.class, id);
        s.get().setCapacity(capacity);
        classroomDao.save(s.get());
    }

    public void updateClassroomName(String id, String name) {
        Optional<Classroom> s = classroomDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(Classroom.class, id);
        s.get().setName(name);
        classroomDao.save(s.get());
    }

    public void updateClassroomNumRows(String id, int numRows) {
        Optional<Classroom> s = classroomDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(Classroom.class, id);
        s.get().setNumRows(numRows);
        classroomDao.save(s.get());
    }

    public void updateClassroomNumCols(String id, int numCols) {
        Optional<Classroom> s = classroomDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(Classroom.class, id);
        s.get().setNumCols(numCols);
        classroomDao.save(s.get());
    }

}
