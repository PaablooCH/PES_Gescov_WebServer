package com.gescov.webserver.service;

import com.gescov.webserver.dao.classroom.ClassroomDao;
import com.gescov.webserver.exception.IsNotAnAdministratorException;
import com.gescov.webserver.exception.NotFoundException;
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
        schoolService.existsSchoolByID(classroom.getSchoolID());
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
        deleteClassSessionsOfAClassroom(id);
        classroomDao.deleteById(id);
    }

    public void deleteClassroomByID(String id, String adminID) {
        Optional<Classroom> c = classroomDao.findById(id);
        if (c.isEmpty()) throw new NotFoundException(Classroom.class, id);
        School s = schoolService.getSchoolByID(c.get().getSchoolID());
        List<String> admins = s.getAdministratorsID();
        if (!admins.contains(adminID)) throw new IsNotAnAdministratorException(User.class, adminID, s.getId());
        deleteClassSessionsOfAClassroom(id);
        classroomDao.deleteById(id);
    }

    private void deleteClassSessionsOfAClassroom(String id) {
        List<ClassSession> cs = classSessionService.getSessionByClassroom(id);
        if (!cs.isEmpty()) {
            for (ClassSession classSes : cs) {
                classSessionService.deleteClassSession(classSes.getId());
            }
        }
    }

    public Classroom updateClassroom(String id, String name, int numRows, int numCols) {
        Optional<Classroom> c = classroomDao.findById(id);
        if (c.isEmpty()) throw new NotFoundException(Classroom.class, id);
        if (!name.equals("")) c.get().setName(name);
        if (numRows != 0) c.get().setNumRows(numRows);
        if (numCols != 0) c.get().setNumCols(numCols);
        return classroomDao.save(c.get());
    }

}
