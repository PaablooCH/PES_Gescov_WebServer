package com.gescov.webserver.dao;

import com.gescov.webserver.model.Contagion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository("contagionMongo")
public class ContagionMongoDB implements ContagionDao{

    @Qualifier("mongoClient")
    @Autowired
    private MongoClient client;
    private MongoCollection<Contagion> contagionCollection;

    @PostConstruct
    void init() {
        contagionCollection = client.getDatabase("Gescov")
                .getCollection("contagion", Contagion.class);
    }

    @Override
    public int insertContagion(Contagion contagion) {
        contagionCollection.insertOne(contagion);
        return 1;
    }
}
