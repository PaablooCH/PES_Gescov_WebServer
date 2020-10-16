package com.gescov.webserver.dao;

import com.gescov.webserver.model.Materia;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface MateriaDao {

    int insertMateria(Materia materia);

    List<Materia> selectAllMaterias();

    Optional<Materia> selectMateriaById(ObjectId id);

    int deleteMateria(ObjectId id);

    int updateMateria(ObjectId id, Materia materia);
}
