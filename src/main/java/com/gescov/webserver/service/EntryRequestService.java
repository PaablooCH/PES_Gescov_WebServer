package com.gescov.webserver.service;

import com.gescov.webserver.dao.entryRequest.EntryRequestDao;
import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.RequestAnsweredException;
import com.gescov.webserver.model.EntryRequest;
import com.gescov.webserver.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EntryRequestService {

    @Autowired
    EntryRequestDao entryRequestDao;

    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    public EntryRequest addEntryRequest(EntryRequest entryRequest) {
        String userID = entryRequest.getUserID();
        String schoolID = entryRequest.getSchoolID();

        userService.existsUser(userID);
        Optional<School> s = schoolService.getSchoolById(schoolID);
        if (s.isEmpty()) throw new NotFoundException(School.class, schoolID);
        EntryRequest req = entryRequestDao.findByUserIDAndSchoolIDAndStatus(userID, schoolID, EntryRequest.RequestState.PENDING);
        if (req != null) throw new AlreadyExistsException(EntryRequest.class, userID + " + " + schoolID + " + status " + EntryRequest.RequestState.PENDING);

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
        if (req.get().getStatus() != EntryRequest.RequestState.PENDING) throw new RequestAnsweredException(EntryRequest.class, requestID);

        req.get().setAnswerDate(LocalDateTime.now());
        req.get().setStatus(EntryRequest.RequestState.valueOf(state));
        entryRequestDao.save(req.get());
    }

}
