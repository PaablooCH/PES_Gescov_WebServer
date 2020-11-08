package com.gescov.webserver.dao.contagion;
/*
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

@Repository("contagionMongo")
public class ContagionDaoImpl implements ContagionDaoCustom{

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
        FindIterable<Contagion> result = contagionCollection.find(eq("infected",contagion.getInfected()));
        boolean insert = true;
        for (Contagion cr : result) {
            if (cr.getEndContagion() == null) {
                insert = false;
                break;
            }
        }
        if (!insert) {
            throw new AlreadyExistsException("Contagion with 'name' " + contagion.getInfected().getName() + " is already infected");
        }
        contagionCollection.insertOne(contagion);
        return 1;
    }

    @Override
    public List<Contagion> selectAllContagion() { //Eficiente Funciona// @Pablo CH
        List<Contagion> allContagion = new ArrayList<>();
        FindIterable<Contagion> result = contagionCollection.find();
        for (Contagion cr : result) {
            allContagion.add(cr);
        }
        return allContagion;
    }

    @Override
    public void updateContagion(String nameInfected) { //Eficiente Funciona// @Pablo CH
        FindIterable<Contagion> result = contagionCollection.find(eq("infected.name",nameInfected));
        for (Contagion cr : result) {
            if (cr.getEndContagion() == null) {
                contagionCollection.findOneAndUpdate(eq("_id", cr.getId()), set("endContagion", LocalDate.now()));
                return;
            }
        }
        throw new NotFoundException(nameInfected + " is not infected at this moment");
    }

    @Override
    public List<Contagion> selectNowContagion(String nameCen) {
        List<Contagion> nowContagion = new ArrayList<>();
        FindIterable<Contagion> result = contagionCollection.find(eq("infected.schools.name", nameCen));
        for (Contagion cr : result) {
            if (cr.getEndContagion() == null) {
                nowContagion.add(cr);
            }
        }
        return nowContagion;
    }

}*/