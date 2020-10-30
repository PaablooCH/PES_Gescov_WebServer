package com.gescov.webserver.dao;

import com.gescov.webserver.model.School;
import org.bson.types.ObjectId;

import java.util.List;

public interface SchoolDao {

    int insertSchool(School school);

    List<School> selectAllSchools();

    School selectSchoolById(ObjectId id);

    School selectSchoolByName(String schoolName);

    int deleteSchoolById(ObjectId id);

    int updateSchoolNameById(ObjectId id, String name);

    int updateSchoolStateById(ObjectId id, String state);

}
