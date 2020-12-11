package com.gescov.webserver.dao.subject;

import com.gescov.webserver.model.Subject;

import java.util.List;

public interface SubjectDaoCustom {

    List<Subject> selectAllBySchoolID(String schoolID);
}
