package com.gescov.webserver.dao.contagion;

import com.gescov.webserver.model.Contagion;

import java.util.List;

public interface ContagionDaoCustom {

    List<Contagion> findInfectedBySchool(String schoolID);

}
