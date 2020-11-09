package com.gescov.webserver.dao.classSession;

import com.gescov.webserver.model.ClassSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassSessionDao extends MongoRepository <ClassSession, String> , ClassSessionDaoCustom<ClassSession, String> {

    List<ClassSession> findAllByHour(String variable);

    List<ClassSession> findAllByDate(String variable);

}
