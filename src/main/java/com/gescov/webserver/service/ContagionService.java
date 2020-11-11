package com.gescov.webserver.service;

import com.gescov.webserver.dao.contagion.ContagionDao;
import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.ZeroInfectedAtSchoolException;
import com.gescov.webserver.model.Contagion;
import com.gescov.webserver.model.User;
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
        if(contagion.getInfectedConfirmed() &&
                contagionDao.existsByEndContagionNullAndInfectedIDAndInfectedConfirmedIsFalse(contagion.getInfectedID()))
            updateContagion(contagion.getInfectedID());
        if (contagionDao.existsByEndContagionNullAndInfectedID(contagion.getInfectedID()))
            throw new AlreadyExistsException(User.class, contagion.getInfectedID());
        return contagionDao.insert(contagion);
    }

    public List<Contagion> getAllContagion() { return contagionDao.findAll(); }

    public String updateContagion(String infectedId) {
        Optional<Contagion> con = contagionDao.findByEndContagionNullAndInfectedID(infectedId);
        if (con.isEmpty()) throw new NotFoundException(User.class, infectedId);
        con.get().setEndContagion(LocalDate.now());
        contagionDao.save(con.get());
        return con.get().getId();
    }

    public List<Contagion> getNowContagion(String idSchool) {
        List<Contagion> con = contagionDao.findInfectedBySchool(idSchool);
        if (con.isEmpty()) throw new ZeroInfectedAtSchoolException(idSchool);
        return con;
    }

    public void existsContagion(String contagionID) {
        if(!contagionDao.existsByEndContagionNullAndId(contagionID)) throw new NotFoundException(Contagion.class, contagionID);
    }
}
