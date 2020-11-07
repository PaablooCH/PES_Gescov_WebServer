package com.gescov.webserver.service;

import com.gescov.webserver.dao.ContagionDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Contagion;
import com.gescov.webserver.model.Subject;
import com.mongodb.client.FindIterable;
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
        //comprobar si existe ya el contagion
        return contagionDao.insert(contagion);
    }

    public List<Contagion> getAllContagion() { return contagionDao.findAll(); }

    public void updateContagion(String idInfected) {
        FindIterable<Contagion> con = contagionDao.findAllByInfected(idInfected);
        for (Contagion cr : con) {
            if (cr.getEndContagion() == null) {
                cr.setEndContagion(LocalDate.now());
                contagionDao.insert(cr);
                return;
            }
        }
        throw new NotFoundException("Contagion with 'id'" + idInfected + "is not infected!");
    }

    //falta comprobar el centro del usuario etc
    //public List<Contagion> getNowContagion(String idCen) { return contagionDao.selectNowContagion(idCen); }
}
