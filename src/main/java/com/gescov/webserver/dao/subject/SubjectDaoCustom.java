package com.gescov.webserver.dao.subject;

import com.gescov.webserver.model.Subject;

import java.util.List;

public interface SubjectDaoCustom<T,ID> {

    List<Subject> selectAllBySchoolId(String Id);

    List<Subject> selectAllBySchoolName(String schoolName);
}
