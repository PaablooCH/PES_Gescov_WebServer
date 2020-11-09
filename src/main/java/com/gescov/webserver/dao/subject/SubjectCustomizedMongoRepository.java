package com.gescov.webserver.dao.subject;

import com.gescov.webserver.model.Subject;

import java.util.List;

public interface SubjectCustomizedMongoRepository <T,ID> {

    List<Subject> selectAllBySchoolName(String schoolName);
}
