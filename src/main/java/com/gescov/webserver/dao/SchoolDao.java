package com.gescov.webserver.dao;

import com.gescov.webserver.model.School;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

public interface SchoolDao {

    int insertSchool(School school);

    List<School> selectAllSchools();

    Optional<School> selectSchoolById(ObjectId id);

    int deleteSchoolById(ObjectId id);

    int updateSchoolById(ObjectId id, School newSchool);

}
