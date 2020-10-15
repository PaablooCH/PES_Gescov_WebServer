package com.gescov.webserver.api;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.service.ClassroomService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/classroom")
@RestController
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping
    public void addClassroom(@NonNull @RequestBody Classroom classroom) {
        classroomService.addClassroom(classroom);
    }

    @GetMapping
    public List<Classroom> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }

    @GetMapping(path = "{id}")
    public Classroom getClassroomById(@PathVariable("id") ObjectId id) throws Exception {
        return classroomService.getClassroomById(id)
                .orElse(null); //throw Exception
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") ObjectId id) {
        classroomService.deleteClassroom(id);
    }

    @PutMapping(path = "{id}")
    public void updateClassroom(@PathVariable("id") ObjectId id, @NonNull @RequestBody Classroom classroomtToUpdate) {
        classroomService.updateClassroom(id, classroomtToUpdate);
    }
}
