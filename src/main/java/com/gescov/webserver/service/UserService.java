package com.gescov.webserver.service;

import com.gescov.webserver.dao.user.UserDao;
import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.NotTeacherException;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    SchoolService schoolService;

    @Autowired
    ContagionService contagionService;

    @Autowired
    AssignmentService assignmentService;

    @Value("${google.api}")
    private String googleAPI;

    public User addUser(User user) {
        return userDao.insert(user);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById(String id) {
        Optional<User> user = userDao.findById(id);
        if(user.isEmpty()) throw new NotFoundException(User.class, id);
        return user.get();
    }

    public User getTeacherByID(String id) {
        Optional<User> user = userDao.findById(id);
        if (user.isEmpty()) throw new NotFoundException(User.class, id);
        if (user.get().isStudent()) throw new NotTeacherException(User.class, id);
        return user.get();
    }

    public List<School> getSchoolsByUser(String id) {
        User us = getUserById(id);
        List<School> sc = new ArrayList<>();
        List<String> aux = us.getSchoolsID();
        for (String s : aux) sc.add(schoolService.getSchoolByID(s));
        return sc;
    }

    public void existsUser(String userID) {
         if (!userDao.existsById(userID)) throw new NotFoundException(User.class, userID);
    }

    public void existsTeacher(String userID) {
        User u = getUserById(userID);
        if (u.isStudent()) throw new NotTeacherException(User.class, userID);
    }

    public void addSchool(String userID, String schoolID) {
        User u = getUserById(userID);
        schoolService.existsSchoolByID(schoolID);
        List<String> schools = u.getSchoolsID();
        if (schools.contains(schoolID)) throw new AlreadyExistsException(School.class, schoolID);
        u.addSchool(schoolID);
        userDao.save(u);
    }

    public void updateUserRisk(String id){
        User u = getUserById(id);
        u.setRisk(!u.isRisk());
        userDao.save(u);
    }

    public List<User> findAllBySchoolID(String schoolID) {
        schoolService.existsSchoolByID(schoolID);
        return userDao.findAllBySchoolID(schoolID);
    }

    public List<User> findAllByIDIn(List<String> infectedIDs) {
        return userDao.findAllByIdIn(infectedIDs);
    }

    @SneakyThrows
    public String verifyToken(String token) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                                                                    .setAudience(Collections.singletonList(googleAPI))
                                                                    .build();
        GoogleIdToken idToken = verifier.verify(token);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            Optional<User> u = userDao.findByTokenID(userId);
            if (u.isPresent()) return u.get().getId();

            // Get profile information from payload
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String pic = (String) payload.get("picture");
            User user = new User(null, name, email, pic);
            user.setTokenID(userId);
            User userCreated = addUser(user);
            return userCreated.getId();
        }
        else return null;
    }

    public void updateUserSchool(String id, String studentID, String schoolID) {
        existsUser(id);
        existsUser(studentID);
        schoolService.isAdmin(id, schoolID);
        addSchool(studentID, schoolID);
    }

    public void deleteSchool(String id) {
        List<User> users = userDao.findAllBySchoolID(id);
        for (User u : users) {
            if (u.getSchoolsID().remove(id)) userDao.save(u);
        }
    }

    public void changeProfile(String id, boolean b){
        User u = getUserById(id);
        u.setStudent(b);
        userDao.save(u);
    }

    public int countInfectedInSchool(String schoolID) {
        int count = 0;
        List<User> users = userDao.findAllBySchoolID(schoolID);
        for (User u : users) {
            if (contagionService.existsInfected(u.getId())) count++;
        }
        return count;
    }

    public void transmitContagion(String userID) {
        assignmentService.transmitContagion(userID);
    }

    public void infect(String userID) {
        contagionService.infect(userID);
    }

    public User addDeviceToken(String userID, String deviceToken){
        User user = getUserById(userID);
        user.addDeviceToken(deviceToken);
        userDao.save(user);
        return user;
    }

    public User deleteDeviceToken(String userID, String deviceToken){
        User user = getUserById(userID);
        user.deleteDeviceToken(deviceToken);
        userDao.save(user);
        return user;
    }

}
