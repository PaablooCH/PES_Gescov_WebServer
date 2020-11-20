package com.gescov.webserver.service;

import com.gescov.webserver.dao.user.UserDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    SchoolService schoolService;

    public User addUser(User user) {
        return userDao.insert(user);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userDao.findById(id);
    }

    public List<String> getSchoolsByUser(String id) {
        Optional<User> us = getUserById(id);
        if (us.isEmpty()) throw new NotFoundException(User.class, id);
        return us.get().getSchoolsID();
    }

    public void existsUser(String userID) {
         if(!userDao.existsById(userID)) throw new NotFoundException(User.class, userID);
    }

    public void addSchool(String id, String schoolID) {
        Optional<User> u = userDao.findById(id);
        if (u.isEmpty()) throw new NotFoundException(User.class, id);
        Optional<School> s = schoolService.getSchoolById(schoolID);
        if (s.isEmpty()) throw new NotFoundException(School.class, schoolID);
        u.get().addSchool(schoolID);
        userDao.save(u.get());
    }

    public void updateUserRisk(String id){
        Optional<User> u = userDao.findById(id);
        if (u.isEmpty()) throw new NotFoundException(User.class, id);
        u.get().setRisk(!u.get().isRisk());
        userDao.save(u.get());
    }

    public List<User> findAllBySchoolID(String schoolID) {
        return userDao.findAllBySchoolID(schoolID);
    }

    public List<User> findAllByIDIn(List<String> infectedIDs) {
        return userDao.findAllByIdIn(infectedIDs);
    }

    @SneakyThrows
    public String verifyToken(String token) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList("673967911868-q9jgm7i3ijsjn0ft3bs6avvpot02a0nl.apps.googleusercontent.com"))
                .build();

        GoogleIdToken idToken = verifier.verify(token);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            Optional<User> u = userDao.findByTokenID(userId);
            if(u.isPresent()) return u.get().getId();

            // Get profile information from payload
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            User user = new User(null, name, email);
            user.setTokenID(userId);
            User userCreated = addUser(user);
            return userCreated.getId();
        } else {
            return null;
        }
    }

    public void updateUserSchool(String id, String studentID, String schoolID) {
        existsUser(id);
        existsUser(studentID);
        schoolService.isAdmin(id, schoolID);
        addSchool(studentID, schoolID);
    }

    public void becomeStudent(String id){
        Optional<User> u = userDao.findById(id);
        if(u.isEmpty()) throw new NotFoundException(User.class, id);
        if(!u.get().getProfile().equals("Student")) u.get().setProfile("Student");
        userDao.save(u.get());
    }

    public void becomeTeacher(String id){
        Optional<User> u = userDao.findById(id);
        if(u.isEmpty()) throw new NotFoundException(User.class, id);
        if(!u.get().getProfile().equals("Teacher")) u.get().setProfile("Teacher");
        userDao.save(u.get());
    }

    public void becomeTutor(String id){
        Optional<User> u = userDao.findById(id);
        if(u.isEmpty()) throw new NotFoundException(User.class, id);
        if(!u.get().getProfile().equals("Tutor")) u.get().setProfile("Tutor");
        userDao.save(u.get());
    }
}
