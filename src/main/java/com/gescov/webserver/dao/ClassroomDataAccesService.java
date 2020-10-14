package com.gescov.webserver.dao;

import com.gescov.webserver.model.Classroom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("classroomMongo")
public class ClassroomDataAccesService implements ClassroomDao {

    private static List<Classroom> db = new ArrayList<>();

    @Override
    public int insertClassroom(UUID id, Classroom classroom) {
        db.add(new Classroom(id, classroom.getName(), classroom.getCapacity()));
        return 1;
    }

    @Override
    public List<Classroom> selectAllClassrooms() {
        return db;
    }

    @Override
    public Optional<Classroom> selectClassroomById(UUID id) {
        return db.stream()
                .filter(classroom -> classroom.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteClassroomById(UUID id) {
        Optional<Classroom> classroomMaybe = selectClassroomById(id);
        if (classroomMaybe.isEmpty()) return 0;
        db.remove(classroomMaybe.get());
        return 1;
    }

    @Override
    public int updateClassroomById(UUID id, Classroom update) {
        return selectClassroomById(id)
                .map(classroom -> {
                    int indexOfClassroomToUpdate = db.indexOf(classroom);
                    if (indexOfClassroomToUpdate >= 0) {
                        db.set(indexOfClassroomToUpdate, new Classroom(id, update.getName(), update.getCapacity()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
