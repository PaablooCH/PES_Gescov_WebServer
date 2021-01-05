package com.gescov.webserver.service;

import com.gescov.webserver.dao.classroom.ClassroomDao;
import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.IsNotAnAdministratorException;
import com.gescov.webserver.exception.NotEqualsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    ClassroomDao classroomDao;

    @Autowired
    SchoolService schoolService;

    @Autowired
    SubjectService subjectService;

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

    public Classroom addSchedule(String id, List<Schedule> schedule) {
        Classroom c = getClassroomById(id);
        for (Schedule s : schedule) {
            Subject su = subjectService.getSubjectById(s.getSubjectID());
            if (!c.getSchoolID().equals(su.getSchoolID()))
                throw new NotEqualsException(School.class, c.getSchoolID(), su.getSchoolID());
        }
        validateSchedule(c, schedule);
        c.setScheduleList(schedule);
        classroomDao.save(c);
        return c;
    }

    public void validateSchedule(Classroom c, List<Schedule> newSchedule) {
        for (Schedule newS : newSchedule) {
            validateHours(newS);
            for (Schedule s : newSchedule) {
                if (s.getSubjectID().equals(newS.getSubjectID()) && s.hashCode() == newS.hashCode()) continue;
                if (!validateSubjectSchedule(s, newS)) throw new AlreadyExistsException(Schedule.class, c.getId());
            }
        }
    }

    public boolean validateSubjectSchedule(Schedule s, Schedule newS) {
        return s.getDay() != newS.getDay() ||
                ((!s.getStart().equals(newS.getStart()) || !s.getEnd().equals(newS.getEnd())) &&
                        (!s.getStart().isAfter(newS.getStart()) || !s.getStart().isBefore(newS.getEnd())) &&
                        (!s.getEnd().isAfter(newS.getStart()) || !s.getEnd().isBefore(newS.getEnd())) &&
                        (!s.getStart().isBefore(newS.getStart()) || !s.getEnd().isAfter(newS.getEnd())));
    }

    public void validateHours(Schedule newS) {
        if (newS.getEnd().isBefore(newS.getStart())) {
            LocalTime aux = newS.getEnd();
            newS.setEnd(newS.getStart());
            newS.setStart(aux);
        }
    }

    public List<Schedule> getScheduleByClassroomId(String id) {
        return getClassroomById(id).getScheduleList();
    }

}
