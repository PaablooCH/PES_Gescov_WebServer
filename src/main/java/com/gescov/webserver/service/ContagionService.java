package com.gescov.webserver.service;

import com.gescov.webserver.dao.contagion.ContagionDao;
import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Contagion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContagionService {

    private final ContagionDao contagionDao;
    private final UserService userService;

    @Autowired
    public ContagionService(ContagionDao contagionDao, UserService userService) {
        this.contagionDao = contagionDao;
        this.userService = userService;
    }

    public Contagion addContagion(Contagion contagion) {
        if (!userService.existsUser(contagion.getInfectedID())) throw new NotFoundException("User with 'id' " +
                contagion.getInfectedID() + " not exists!");
        Optional<Contagion> con = contagionDao.findByEndContagionNullAndInfectedID(contagion.getInfectedID());
        if (con.isPresent()) throw new AlreadyExistsException("Contagion with 'id' " + contagion.getInfectedID() +
                " is already infected!");
        return contagionDao.insert(contagion);
    }

    public List<Contagion> getAllContagion() { return contagionDao.findAll(); }

    public void updateContagion(String infectedId) {
        Optional<Contagion> con = contagionDao.findByEndContagionNullAndInfectedID(infectedId);
        if (con.isEmpty()) throw new NotFoundException("Contagion with 'id' " + infectedId + " is not infected!");
        con.get().setEndContagion(LocalDate.now());
        contagionDao.save(con.get());
    }

    public List<Contagion> getNowContagion(String idSchool) {
        List<Contagion> con = contagionDao.findInfectedBySchool(idSchool);
        if (con.isEmpty()) throw new NotFoundException("In School " + idSchool + " doesn't exists Contagions");
        return con;
    }

}
