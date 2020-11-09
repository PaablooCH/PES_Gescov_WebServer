package com.gescov.webserver.dao.classSession;

import com.gescov.webserver.model.ClassSession;

import java.util.List;

public interface ClassSessionDaoCustom<T, ID> {

    List<ClassSession> selectAllByClassroomId(String variable);

    List<ClassSession> selectAllBySubjectId(String variable);

    List<ClassSession> selectAllByTeacherId(String variable);


}
