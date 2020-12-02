package com.gescov.webserver.dao.contagion;

import com.gescov.webserver.model.Contagion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContagionDao extends MongoRepository<Contagion, String>, ContagionDaoCustom<Contagion, String>{

    Optional <Contagion> findByEndContagionNullAndInfectedID(String infectedId);

    Boolean existsByEndContagionNullAndInfectedID(String infectedID);

    Boolean existsByEndContagionNullAndInfectedIDAndInfectedConfirmedIsFalse(String infectedID);

    Boolean existsByEndContagionNullAndId(String contagionID);

    Boolean existsByInfectedID(String contagionID);

    List <Contagion> findAllByEndContagionNullAndInfectedIDIn(List<String> infectedIDs);

    List <Contagion> findAllByEndContagionNotNull();

}
