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

    public Classroom getClassroomById(String id) {
        Optional<Classroom> classroom = classroomDao.findById(id);
        if (classroom.isEmpty()) throw new NotFoundException(Classroom.class, id);
        return classroom.get();
    }

    public Pair<Integer, Integer> getClassroomDistributionById(String id) {
        Classroom c = getClassroomById(id);
        return Pair.of(c.getNumRows(), c.getNumCols());
    }

    public void deleteClassroom(String id) {
        deleteClassSessionsOfAClassroom(id);
        classroomDao.deleteById(id);
    }

    public void deleteClassroomByID(String id, String adminID) {
        Classroom c = getClassroomById(id);
        School s = schoolService.getSchoolByID(c.getSchoolID());
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
        Classroom c = getClassroomById(id);
        if (!name.equals("")) c.setName(name);
        if (numRows != 0) c.setNumRows(numRows);
        if (numCols != 0) c.setNumCols(numCols);
        return classroomDao.save(c);
    }

}
