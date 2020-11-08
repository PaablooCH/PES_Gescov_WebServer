package com.gescov.webserver.service;

import com.gescov.webserver.dao.contagion.ContagionDao;
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

    @Autowired
    public ContagionService(ContagionDao contagionDao) {
        this.contagionDao = contagionDao;
    }

    public Contagion addContagion(Contagion contagion) {
        Optional<Contagion> con = contagionDao.findByEndContagionNullAndInfected_Id(contagion.getInfected().getId());
        if (con.isPresent()) throw new NotFoundException("Contagion with 'id'" + contagion.getInfected().getId() + "is already infected!");
        return contagionDao.insert(contagion);
    }

    public List<Contagion> getAllContagion() { return contagionDao.findAll(); }

    public void updateContagion(String infectedId) {
        Optional<Contagion> con = contagionDao.findByEndContagionNullAndInfected_Id(infectedId);
        if (con.isEmpty()) throw new NotFoundException("Contagion with 'id'" + infectedId + "is not infected!");
        con.get().setEndContagion(LocalDate.now());
        contagionDao.insert(con.get());
    }

    public List<Contagion> getNowContagion(String idSchool) {
        return contagionDao.findByInfectedSchools(idSchool); //revisar esta funcion que pide una lista de Schools
    }

}
