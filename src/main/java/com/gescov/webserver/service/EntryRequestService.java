package com.gescov.webserver.service;

import com.gescov.webserver.dao.entryRequest.EntryRequestDao;
import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.RequestAnsweredException;
import com.gescov.webserver.model.EntryRequest;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<Pair<EntryRequest, String>> getRequestsBySchool(String schoolID) {
        Optional<School> s = schoolService.getSchoolById(schoolID);
        if (s.isEmpty()) throw new NotFoundException(School.class, schoolID);

        List<Pair<EntryRequest, String>> result = new ArrayList<>();
        List<EntryRequest> reqs = entryRequestDao.findBySchoolID(schoolID);
        if (!reqs.isEmpty()) {
            for (EntryRequest req : reqs) {
                Optional<User> us = userService.getUserById(req.getUserID());
                if (us.isEmpty()) throw new NotFoundException(User.class, req.getUserID());
                String userName = us.get().getName();
                result.add(Pair.of(req, userName));
            }
        }
        return result;
    }

    public List<EntryRequest> getRequestsByUser(String userID) {
        userService.existsUser(userID);
        return entryRequestDao.findByUserID(userID);
    }

    public void updateRequestState(String requestID, String adminID, String state) {
        Optional<EntryRequest> req = entryRequestDao.findById(requestID);
        if (req.isEmpty()) throw new NotFoundException(EntryRequest.class, requestID);
        EntryRequest request = req.get();
        if (request.getStatus() != EntryRequest.RequestState.PENDING) throw new RequestAnsweredException(EntryRequest.class, requestID);
        schoolService.isAdmin(request.getSchoolID(), adminID);

        request.setAnswerDate(LocalDateTime.now());
        request.setStatus(EntryRequest.RequestState.valueOf(state));
        entryRequestDao.save(request);
        if ((EntryRequest.RequestState.valueOf(state)).equals(EntryRequest.RequestState.ACCEPTED)) {
            userService.addSchool(request.getUserID(), request.getSchoolID());
        }
    }

}
