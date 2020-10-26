package com.gescov.webserver.service;

import com.gescov.webserver.dao.ContagionDao;
import com.gescov.webserver.model.Contagion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContagionService {

    private final ContagionDao contagionDao;

    @Autowired
    public ContagionService(@Qualifier("contagionMongo")ContagionDao contagionDao) {
        this.contagionDao = contagionDao;
    }

    public int addContagion(Contagion contagion) { return contagionDao.insertContagion(contagion); }

    public List<Contagion> getAllContagion() { return contagionDao.selectAllContagion(); }

    public int updateContagion(String nameInfected) {
        return contagionDao.updateContagion(nameInfected);
    }

    public List<Contagion> getNowContagion(String nameCen) { return contagionDao.selectNowContagion(nameCen); }
}
