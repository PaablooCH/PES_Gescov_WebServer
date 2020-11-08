package com.gescov.webserver.dao;

import com.gescov.webserver.model.Contagion;
import com.mongodb.client.FindIterable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContagionDao extends MongoRepository<Contagion, String>, ContagionDaoCustom{

    List<Contagion> selectNowContagion(String nameCen);

    Optional<Contagion> findByEndContagionNullAndInfected_Id(String infectedId);
}
