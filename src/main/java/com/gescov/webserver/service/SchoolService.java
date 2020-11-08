package com.gescov.webserver.service;

import com.gescov.webserver.dao.school.SchoolDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    private final SchoolDao schoolDao;

    @Autowired
    public SchoolService(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }


    public School addSchool(School school) {
        return schoolDao.insert(school);
    }

    public List<School> getAllSchools() {
        return schoolDao.findAll();
    }

    public Optional<School> getSchoolById(String id) {
        return schoolDao.findById(id);
    }

    public School getSchoolByName(String schoolName) {
        return schoolDao.findByName(schoolName);
    }

    public void deleteSchool(String id) {
        schoolDao.deleteById(id);
    }

    public void updateSchoolName(String id, String update) {
        Optional<School> s = schoolDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException("School with 'id'" + id + "not found!");
        s.get().setName(update);
        schoolDao.save(s.get());
    }

    public void updateSchoolState(String id, String update) {
        Optional<School> s = schoolDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException("School with 'id'" + id + "not found!");
        s.get().setState(update);
        schoolDao.save(s.get());
    }

}