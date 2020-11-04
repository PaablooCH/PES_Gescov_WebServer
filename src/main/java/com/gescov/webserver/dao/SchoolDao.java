package com.gescov.webserver.dao;

import com.gescov.webserver.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolDao extends MongoRepository<School, String> {

    School findByName(String schoolName);

}
