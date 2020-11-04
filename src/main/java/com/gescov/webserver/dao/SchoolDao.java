package com.gescov.webserver.dao;

import com.gescov.webserver.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolDao extends MongoRepository<School, String> {

    School findAllByName(String schoolName);

}
