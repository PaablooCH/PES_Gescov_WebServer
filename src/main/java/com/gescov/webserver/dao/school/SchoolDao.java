package com.gescov.webserver.dao.school;

import com.gescov.webserver.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolDao extends MongoRepository<School, String>, SchoolDaoCustom<School, String> {

    School findByName(String schoolName);

    boolean existsByIdAndCreatorID(String schoolID, String creatorID);

}
