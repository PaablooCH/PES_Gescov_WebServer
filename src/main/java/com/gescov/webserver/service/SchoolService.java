package com.gescov.webserver.service;

import com.gescov.webserver.dao.school.SchoolDao;
import com.gescov.webserver.exception.IsNotAnAdministratorException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.Subject;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    @Autowired
    SchoolDao schoolDao;

    @Autowired
    UserService userService;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    SubjectService subjectService;

    public School addSchool(School school) {
        String creatorID = school.getCreatorID();
        userService.existsUser(creatorID);
        schoolDao.insert(school);
        userService.addSchool(creatorID, school.getId());
        return school;
    }

    public void addAdministrator(String schoolID, String adminID, String newAdminID) {
        Optional<School> s = schoolDao.findById(schoolID);
        if (s.isEmpty()) throw new NotFoundException(School.class, schoolID);
        if (!s.get().getAdministratorsID().contains(adminID)) throw new IsNotAnAdministratorException(User.class, adminID);
        userService.existsUser(newAdminID);
        s.get().addAdministrator(newAdminID);
        schoolDao.save(s.get());
    }

    public List<School> getAllSchools() {
        return schoolDao.findAll();
    }

    public School getSchoolByName(String schoolName) {
        return schoolDao.findByName(schoolName);
    }

    public void deleteSchool(String id, String adminID) {
        Optional<School> s = schoolDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(School.class, id);
        List<String> admins = s.get().getAdministratorsID();
        if (!admins.contains(adminID)) throw new IsNotAnAdministratorException(User.class, adminID);
        schoolDao.deleteById(id);
        deleteAllClassroomsOfSchool(id);
        deleteAllSubjectsOfSchool(id);
        userService.deleteSchool(id);
    }

    private void deleteAllSubjectsOfSchool(String id) {
        List<Subject> su = subjectService.selectSubjectBySchoolId(id);
        for (Subject sub : su) subjectService.deleteSubject(sub.getId());
    }

    private void deleteAllClassroomsOfSchool(String id) {
        List<Classroom> c = classroomService.getClassroomsBySchoolID(id);
        for (Classroom cl : c) classroomService.deleteClassroom(cl.getId());
    }

    public School updateSchool(String id, String name, float latitude, float longitude, String phone, String website, String address) {
        Optional<School> s = schoolDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(School.class, id);
        School sc = s.get();
        if (!name.equals("")) sc.setName(name);
        if (longitude != 0.0) sc.setLongitude(longitude);
        if (latitude != 0.0) sc.setLongitude(latitude);
        if (phone.equals("")) sc.setPhone(phone);
        if (!website.equals("")) sc.setWebsite(website);
        if (!address.equals("")) sc.setAddress(address);
        return schoolDao.save(sc);
    }

    public void updateSchoolName(String id, String update) {
        Optional<School> s = schoolDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(School.class, id);
        s.get().setName(update);
        schoolDao.save(s.get());
    }

    public void updateSchoolState(String id, String update) {
        Optional<School> s = schoolDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(School.class, id);
        s.get().setState(update);
        schoolDao.save(s.get());
    }

    public void isAdmin(String schoolID, String adminID) {
        if (!schoolDao.existsByIdAndAdministratorsIDIn(schoolID, adminID)) throw new IsNotAnAdministratorException(User.class, adminID);
    }

    public List<Pair<School, Integer>> getSchoolsAndNumInfected() {
        List<School> schoolList = schoolDao.findAll();
        if (schoolList.isEmpty()) return null;
        int punctuation;
        List<Pair<School, Integer>> aux = new ArrayList<>();
        for (School school : schoolList) {
            punctuation = userService.countInfectedInSchool(school.getId());
            aux.add(Pair.of(school, punctuation));
        }
        return aux;
    }

    public School getSchoolByID(String id) {
        Optional <School> sc = schoolDao.findById(id);
        if (sc.isEmpty()) throw new NotFoundException(School.class, id);
        return sc.get();
    }

    public void existsSchoolByID(String schoolID) {
        Optional <School> sc = schoolDao.findById(schoolID);
        if (sc.isEmpty()) throw new NotFoundException(School.class, schoolID);
    }
}