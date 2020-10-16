package com.gescov.webserver.dao;

import com.gescov.webserver.model.Contagion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

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

    @Override
    public List<Contagion> selectAllContagion() {
        List<Contagion> allContagion = new ArrayList<>();
        FindIterable<Contagion> result = contagionCollection.find();
        for (Contagion cr : result) {
            Contagion c = new Contagion(cr.getId(), cr.getNameInfected());
            c.setStartContagion(cr.getStartContagion());
            c.setEndContagion(cr.getEndContagion());
            allContagion.add(c);
        }
        return allContagion;
    }

    @Override
    public int updateContagion(String nameInfected) {
        FindIterable<Contagion> result = contagionCollection.find();
        for (Contagion cr : result) {
            System.out.println(cr.getNameInfected() + " " + cr.getEndContagion() + nameInfected);
            if (cr.getNameInfected().equals(nameInfected) && cr.getEndContagion() == null) {
                ObjectId id = cr.getId();
                contagionCollection.findOneAndUpdate(eq("_id", id), set("endContagion", new Date()));
            }
        }
        return 1;
    }

}
