package com.gescov.webserver.dao.contagion;

import com.gescov.webserver.model.Contagion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContagionDao extends MongoRepository<Contagion, String>, ContagionDaoCustom{

    List<Contagion> selectNowContagion(String nameCen);

    Optional<Contagion> findByEndContagionNullAndInfected_Id(String infectedId);

    List<Contagion> findByInfectedSchools(String idSchool); //revisar esta funcion que pide una lista de Schools

}
