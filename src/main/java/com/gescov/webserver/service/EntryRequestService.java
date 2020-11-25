package com.gescov.webserver.service;

import com.gescov.webserver.dao.entryRequest.EntryRequestDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.EntryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EntryRequestService {

    @Autowired
    EntryRequestDao entryRequestDao;

    public EntryRequest addEntryRequest(EntryRequest entryRequest) {
        entryRequestDao.insert(entryRequest);
        return entryRequest;
    }

    public List<EntryRequest> getRequestsBySchool(String schoolID) {
        return entryRequestDao.findBySchoolID(schoolID);
    }

    public List<EntryRequest> getRequestsByUser(String userID) {
        return entryRequestDao.findByUserID(userID);
    }

    public void updateRequestState(String requestID, String state) {
        Optional<EntryRequest> req = entryRequestDao.findById(requestID);
        if (req.isEmpty()) throw new NotFoundException(EntryRequest.class, requestID);
        req.get().setAnswerDate(LocalDateTime.now());
        req.get().setStatus(EntryRequest.RequestState.valueOf(state));
        entryRequestDao.save(req.get());
    }

}
