package com.gescov.webserver.api;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public Classroom getClassroomById(@PathVariable("id") UUID id) throws Exception {
        return classroomService.getClassroomById(id)
                .orElse(null); //throw Exception
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        classroomService.deleteClassroom(id);
    }

    @PutMapping(path = "{id}")
    public void updateClassroom(@PathVariable("id") UUID id, @NonNull @RequestBody Classroom classroomtToUpdate) {
        classroomService.updateClassroom(id, classroomtToUpdate);
    }
}
