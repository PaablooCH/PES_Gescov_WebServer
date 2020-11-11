package com.gescov.webserver.service;

import com.gescov.webserver.dao.contagion.ContagionDao;
import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.ZeroInfectedAtSchoolException;
import com.gescov.webserver.model.Contagion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContagionService {

    @Autowired
    ContagionDao contagionDao;

    @Autowired
    UserService userService;


    public Contagion addContagion(Contagion contagion) {
        userService.existsUser(contagion.getInfectedID());
        Optional<Contagion> con = contagionDao.findByEndContagionNullAndInfectedID(contagion.getInfectedID());
        if (con.isPresent()) throw new AlreadyExistsException(Contagion.class, contagion.getInfectedID());
        return contagionDao.insert(contagion);
    }

    public List<Contagion> getAllContagion() { return contagionDao.findAll(); }

    public void updateContagion(String infectedId) {
        Optional<Contagion> con = contagionDao.findByEndContagionNullAndInfectedID(infectedId);
        if (con.isEmpty()) throw new NotFoundException(Contagion.class, infectedId);
        con.get().setEndContagion(LocalDate.now());
        contagionDao.save(con.get());
    }

    public List<Contagion> getNowContagion(String idSchool) {
        List<Contagion> con = contagionDao.findInfectedBySchool(idSchool);
        if (con.isEmpty()) throw new ZeroInfectedAtSchoolException(idSchool);
        return con;
    }

    public void existsContagion(String userID) {
        if(!contagionDao.existsByEndContagionNullAndInfectedID(userID)) throw new NotFoundException(Contagion.class, userID);
    }
}
