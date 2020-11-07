package com.gescov.webserver.dao;

import com.gescov.webserver.model.Contagion;
import com.mongodb.client.FindIterable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContagionDao extends MongoRepository<Contagion, String>, ContagionDaoCustom{

    List<Contagion> selectNowContagion(String nameCen);

    FindIterable<Contagion> findAllByInfected(String idInfected);
}
