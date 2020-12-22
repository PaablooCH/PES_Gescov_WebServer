package com.gescov.webserver.dao.wall;

import com.gescov.webserver.model.WallEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WallEntryDao extends MongoRepository<WallEntry, String> {

    List<WallEntry> getAllBySchoolID(String schoolID);

}
