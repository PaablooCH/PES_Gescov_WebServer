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

    @Autowired
    NotificationService notificationService;

    public EntryRequest addEntryRequest(EntryRequest entryRequest) {
        String userID = entryRequest.getUserID();
        String schoolID = entryRequest.getSchoolID();

        User applicant = userService.getUserById(userID);
        School school = schoolService.getSchoolByID(schoolID);
        EntryRequest req = entryRequestDao.findByUserIDAndSchoolIDAndStatus(userID, schoolID, EntryRequest.RequestState.PENDING);
        if (req != null) throw new AlreadyExistsException(EntryRequest.class, userID + " + " + schoolID + " + status " + EntryRequest.RequestState.PENDING);

        entryRequestDao.insert(entryRequest);
        sendNotiToAdmins(school, applicant);

        return entryRequest;
    }

    private void sendNotiToAdmins(School school, User applicant) {
        List<String> adminsID = school.getAdministratorsID();
        List<User> admins = new ArrayList<>();
        for (String id : adminsID) admins.add(userService.getUserById(id));
        for (User us : admins) {
            for (String token : us.getDevices()) {
                notificationService.sendNotiToDevice(token, "You have received an entry request!", applicant.getName() + " requested entering at " +
                                                                   school.getName() + "!");
            }
        }
    }

    public List<Pair<EntryRequest, String>> getRequestsBySchool(String schoolID) {
        schoolService.existsSchoolByID(schoolID);
        List<Pair<EntryRequest, String>> result = new ArrayList<>();
        List<EntryRequest> reqs = entryRequestDao.findBySchoolID(schoolID);
        if (!reqs.isEmpty()) {
            for (EntryRequest req : reqs) {
                User us = userService.getUserById(req.getUserID());
                String userName = us.getName();
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

        User applicant = userService.getUserById(request.getUserID());
        School schoolApplied = schoolService.getSchoolByID(request.getSchoolID());

        request.setAnswerDate(LocalDateTime.now());
        request.setStatus(EntryRequest.RequestState.valueOf(state));
        entryRequestDao.save(request);
        if ((EntryRequest.RequestState.valueOf(state)).equals(EntryRequest.RequestState.ACCEPTED)) {
            userService.addSchool(applicant.getId(), schoolApplied.getId());
            sendNotiToUser(applicant, schoolApplied, true);
        }
        else sendNotiToUser(applicant, schoolApplied, false);
    }

    private void sendNotiToUser(User applicant, School school, boolean isAccepted) {
        for (String token : applicant.getDevices()) {
            if (isAccepted) notificationService.sendNotiToDevice(token, "You have been accepted at " +
                    school.getName() + ".", "");
            else notificationService.sendNotiToDevice(token, "You have been rejected at " +
                    school.getName() + ".", "");
        }
    }

}
