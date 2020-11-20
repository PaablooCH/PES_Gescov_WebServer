package com.gescov.webserver.dao.tracingTest;

import com.gescov.webserver.model.TracingTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TracingTestDao extends MongoRepository<TracingTest, String> {

    List<TracingTest> findAllByContagionID(String userID);

    void deleteAllByContagionID(String userID);

}
