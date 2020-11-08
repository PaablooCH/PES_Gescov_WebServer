package com.gescov.webserver.dao.classroom;

import com.gescov.webserver.model.Classroom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomDao extends MongoRepository<Classroom, String>, ClassroomCustomizedMongoRepository<Classroom, String> {

}

