package com.gescov.webserver.dao.classroom;

import com.gescov.webserver.model.Classroom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomDao extends MongoRepository<Classroom, String>, ClassroomDaoCustom<Classroom, String> {

    @Query(value = "{'id' : ?0}", fields ="{'id' : 0, 'name' : 0, 'capacity': 0, 'schoolID' :0}")
    Classroom findClassroomById(String id);

}

