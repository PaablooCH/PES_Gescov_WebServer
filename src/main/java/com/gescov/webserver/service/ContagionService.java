package com.gescov.webserver.service;

import com.gescov.webserver.dao.contagion.ContagionDao;
import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.UNeedToWait;
import com.gescov.webserver.exception.ZeroInfectedAtSchoolException;
import com.gescov.webserver.model.Contagion;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ContagionService {

    @Autowired
    ContagionDao contagionDao;

    @Autowired
    UserService userService;

    @Autowired
    TracingTestService tracingTestService;

    public Contagion addContagion(Contagion contagion) {
        userService.existsUser(contagion.getInfectedID());
        if(contagion.getInfectedConfirmed() &&
                contagionDao.existsByEndContagionNullAndInfectedIDAndInfectedConfirmedIsFalse(contagion.getInfectedID()))
            updateContagion(contagion.getInfectedID());
        else if (contagionDao.existsByEndContagionNullAndInfectedID(contagion.getInfectedID()))
            throw new AlreadyExistsException(User.class, contagion.getInfectedID());
        else if (contagionDao.existsByInfectedIDAndEndContagionNotNull(contagion.getInfectedID()))
            throw new UNeedToWait(contagion.getInfectedID());
        if(contagion.getInfectedConfirmed()) userService.transmitContagion(contagion.getInfectedID());
        return contagionDao.insert(contagion);
    }

    public List<Contagion> getAllContagion() { return contagionDao.findAll(); }

    public void updateContagion(String infectedID) {
        Optional<Contagion> con = contagionDao.findByEndContagionNullAndInfectedID(infectedID);
        if (con.isEmpty()) throw new NotFoundException(User.class, infectedID);
        con.get().setEndContagion(LocalDate.now());
        contagionDao.save(con.get());
    }

    public List<Pair<String, LocalDate>> getNowContagion(String schoolID) {
        List<Contagion> con = getContagions(schoolID);
        if (con.isEmpty()) throw new ZeroInfectedAtSchoolException(schoolID);
        List<User> us = getUsers(con);
        List<Pair<String, LocalDate>> aux = new ArrayList<>();
        for(int i = 0; i < con.size(); i++) aux.add(Pair.of(us.get(i).getName(), con.get(i).getStartContagion()));
        return aux;
    }

    private List<Contagion> getContagions(String schoolID) {
        List<User> infected = userService.findAllBySchoolID(schoolID);
        List<String> infectedIDs = new ArrayList<>();
        for (User in : infected) {
            infectedIDs.add(in.getId());
        }
        return contagionDao.findAllByEndContagionNullAndInfectedIDIn(infectedIDs);
    }

    private List<User> getUsers(List<Contagion> con) {
        List<String> infectedIDs = new ArrayList<>();
        for (Contagion in : con) {
            infectedIDs.add(in.getInfectedID());
        }
        return userService.findAllByIDIn(infectedIDs);
    }

    public boolean existsContagion(String contagionID) {
        return contagionDao.existsByEndContagionNullAndId(contagionID);
    }

    public boolean existsInfected(String contagionID) {
        return contagionDao.existsByEndContagionNullAndInfectedID(contagionID);
    }

    public String getContagionByUser(String infectedID) {
        Optional<Contagion> con = contagionDao.findByEndContagionNullAndInfectedID(infectedID);
        if (con.isEmpty()) throw new NotFoundException(User.class, infectedID);
        return con.get().getId();
    }

    public void infect(String infectedID) {
        Contagion contagion = new Contagion(null, infectedID, false);
        contagionDao.insert(contagion);
    }

    public void deleteContagion(LocalDate date) {
        List <Contagion> con = contagionDao.findAllByEndContagionNotNull();
        for (Contagion c : con) {
            if (DAYS.between(c.getEndContagion(), date) >= 2) {
                tracingTestService.deleteAllTracingTest(c.getId());
                contagionDao.delete(c);
            }
        }
    }

}
