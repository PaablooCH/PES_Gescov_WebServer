package com.gescov.webserver.dao;

import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository("userMongo")
public class UserMongoDB implements UserDao {

    @Qualifier("mongoClient")
    @Autowired
    private MongoClient client;
    private MongoCollection<User> userCollection;

    @PostConstruct
    void init() {
        userCollection = client.getDatabase("Gescov").getCollection("users", User.class);
    }

    @Override
    public int insertUser(User user) {
        userCollection.insertOne(user);
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> allUsers = new ArrayList<>();
        FindIterable<User> result = userCollection.find();
        for (User us : result) allUsers.add(us);
        return allUsers;
    }

    @Override
    public List<School> getUserSchools(ObjectId id) {
        return selectUserById(id).getSchools();
    }

    @Override
    public User selectUserById(ObjectId id) {
        User us = userCollection.find(eq("_id", id)).first(); //first?
        if (us == null) throw new NotFoundException("User with 'id' " + id + " not found!");
        return us;
    }

}
