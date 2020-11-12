package com.gescov.webserver.service;

import com.gescov.webserver.dao.user.UserDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

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

    public void addSchool(String id, String update) {
        Optional<User> u = userDao.findById(id);
        if (u.isEmpty()) throw new NotFoundException(User.class, id);
        u.get().addSchool(update);
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
    public Boolean verifyToken(String token) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList("673967911868-hubhos2mmf4fqmegetr2kh3pc4o65vhi.apps.googleusercontent.com"))
                .build();

        GoogleIdToken idToken = verifier.verify(token);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            System.out.println(email + " " + name + " " + familyName + " " + givenName);
            return true;
        } else {
            System.out.println("Invalid ID token.");
            return false;
        }
    }
}
