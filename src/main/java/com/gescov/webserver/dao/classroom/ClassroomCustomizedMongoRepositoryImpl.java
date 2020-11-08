package com.gescov.webserver.dao.classroom;

import com.gescov.webserver.model.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.util.Pair;

import java.util.List;

public class ClassroomCustomizedMongoRepositoryImpl<T, ID> implements ClassroomCustomizedMongoRepository<T, ID> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Classroom> selectSchoolClassrooms(String schoolName) {
        return null;
    }

    @Override
    public Pair<Integer, Integer> selectClassroomDistributionById(String id) {
        return null;
    }
}
