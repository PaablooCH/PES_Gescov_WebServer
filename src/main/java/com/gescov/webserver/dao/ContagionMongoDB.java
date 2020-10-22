package com.gescov.webserver.dao;

import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Contagion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
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
    public int insertContagion(Contagion contagion) { //Eficiente Funciona// @Pablo CH
        FindIterable<Contagion> result = contagionCollection.find(eq("nameInfected",contagion.getNameInfected()));
        boolean insert = true;
        for (Contagion cr : result) {
            if (cr.getEndContagion() == null) {
                insert = false;
                break;
            }
        }
        if (!insert) {
            throw new AlreadyExistsException("Contagion with 'name' " + contagion.getNameInfected() + " is already infected");
        }
        contagionCollection.insertOne(contagion);
        return 1;
    }

    @Override
    public List<Contagion> selectAllContagion() { //Eficiente Funciona// @Pablo CH
        List<Contagion> allContagion = new ArrayList<>();
        FindIterable<Contagion> result = contagionCollection.find();
        for (Contagion cr : result) {
            allContagion.add(new Contagion(cr.getId(),
                    cr.getNameInfected(), cr.getStartContagion(), cr.getEndContagion()));
        }
        return allContagion;
    }

    @Override
    public int updateContagion(String nameInfected) { //Eficiente Funciona// @Pablo CH
        FindIterable<Contagion> result = contagionCollection.find(eq("nameInfected",nameInfected));
        for (Contagion cr : result) {
            if (cr.getEndContagion() == null) {
                contagionCollection.findOneAndUpdate(eq("_id", cr.getId()), set("endContagion", new Date()));
                return 1;
            }
        }
        throw new NotFoundException(nameInfected + " is not infected at this moment");
    }

    @Override
    public List<Contagion> selectNowContagion() {
        List<Contagion> nowContagion = new ArrayList<>();
        FindIterable<Contagion> result = contagionCollection.find(eq("endContagion",null));
        if (result.first() == null) throw new NotFoundException("Nobody is infected");
        for (Contagion cr : result) {
            nowContagion.add(new Contagion(cr.getId(),
                    cr.getNameInfected(), cr.getStartContagion(), cr.getEndContagion()));
        }
        return nowContagion;
    }

}