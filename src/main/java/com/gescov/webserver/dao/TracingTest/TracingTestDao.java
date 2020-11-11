package com.gescov.webserver.dao.TracingTest;

import com.gescov.webserver.model.TracingTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TracingTestDao extends MongoRepository<TracingTest, String>, TracingTestDaoCustom<TracingTest, String> {

    List<TracingTest> findAllByUserID(String userID);

    void deleteAllByUserID(String userID);

}
