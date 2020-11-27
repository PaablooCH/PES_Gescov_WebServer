package com.gescov.webserver.dao.entryRequest;

import com.gescov.webserver.model.EntryRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRequestDao extends MongoRepository<EntryRequest, String>  {

    List<EntryRequest> findBySchoolID(String schoolID);

    List<EntryRequest> findByUserID(String userID);

    EntryRequest findByUserIDAndSchoolIDAndStatus(String userID, String schoolID, EntryRequest.RequestState status);

}
