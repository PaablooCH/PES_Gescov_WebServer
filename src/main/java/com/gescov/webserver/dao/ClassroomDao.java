package com.gescov.webserver.dao;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.School;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomDao extends MongoRepository<Classroom, String>, ClassroomCustomizedMongoRepository<Classroom, String> {

}

