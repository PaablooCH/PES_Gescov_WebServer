package com.gescov.webserver.dao;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.Contagion;

import java.util.List;

public interface ContagionDao {

    int insertContagion(Contagion contagion);

    List<Contagion> selectAllContagion();

    int updateContagion(String nameInfected);
}
