package com.gescov.webserver.service;

import com.gescov.webserver.dao.SchoolDao;
import com.gescov.webserver.model.School;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolDao schoolDao;

    @Autowired
    public SchoolService(@Qualifier("schoolMongo")SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    public int addSchool(School school) {
        return schoolDao.insertSchool(school);
    }

    public List<School> getAllSchools() {
        return schoolDao.selectAllSchools();
    }

    public School getSchoolById(ObjectId id) {
        return schoolDao.selectSchoolById(id);
    }

    public int deleteSchool(ObjectId id) {
        return schoolDao.deleteSchoolById(id);
    }

    public int updateSchoolName(ObjectId id, String name) {
        return schoolDao.updateSchoolNameById(id, name);
    }

    public int updateSchoolState(ObjectId id, String state) {
        return schoolDao.updateSchoolStateById(id, state);
    }

}