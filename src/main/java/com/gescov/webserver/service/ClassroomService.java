package com.gescov.webserver.service;

import com.gescov.webserver.dao.classroom.ClassroomDao;
import com.gescov.webserver.exception.IsNotAnAdministratorException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.WrongCapacityException;
import com.gescov.webserver.model.ClassSession;
import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
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

    @Autowired
    ClassSessionService classSessionService;


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
        DeleteClassSessionsOfAClassroom(id);
        classroomDao.deleteById(id);
    }

    public void deleteClassroomByID(String id, String adminID) {
        Optional<Classroom> c = classroomDao.findById(id);
        if (c.isEmpty()) throw new NotFoundException(Classroom.class, id);
        Optional<School> s = schoolService.getSchoolById(c.get().getSchoolID());
        if (s.isEmpty()) throw new NotFoundException(School.class, c.get().getSchoolID());
        List<String> admins = s.get().getAdministratorsID();
        if(!admins.contains(adminID)) throw new IsNotAnAdministratorException(User.class, adminID);
        DeleteClassSessionsOfAClassroom(id);
        classroomDao.deleteById(id);
    }

    private void DeleteClassSessionsOfAClassroom(String id) {
        List<ClassSession> cs = classSessionService.getSessionByClassroom(id);
        if(!cs.isEmpty()) {
            for (ClassSession classSes : cs) {
                classSessionService.deleteClassSession(classSes.getId());
            }
        }
    }

    public Classroom updateClassroom(String id, int capacity, String name, int numRows, int numCols) {
        Optional<Classroom> c = classroomDao.findById(id);
        if (c.isEmpty()) throw new NotFoundException(Classroom.class, id);
        if (!(capacity == 0)) c.get().setCapacity(capacity);
        if (!name.equals(""))c.get().setName(name);
        if (!(numRows == 0)) c.get().setNumRows(numRows);
        if (!(numCols == 0)) c.get().setNumCols(numCols);
        return classroomDao.save(c.get());
    }
/*
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
*/
}
