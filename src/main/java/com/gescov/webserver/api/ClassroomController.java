package com.gescov.webserver.api;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/classrooms")
@RestController
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }


    @PostMapping
    public Classroom addClassroom(@RequestBody Classroom classroom) {
        return classroomService.addClassroom(classroom);
    }

    @GetMapping
    public List<Classroom> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }

    @GetMapping(path = "/school")
    public List<Classroom> getClassroomsBySchool(@NotNull @RequestParam("schoolName") String schoolName)  {
        return classroomService.getClassroomsBySchool(schoolName);
    }

    @GetMapping(path = "/school/{id}")
    public List<Classroom> getClassroomsBySchoolID(@PathVariable("id") String schoolID)  {
        return classroomService.getClassroomsBySchoolID(schoolID);
    }

    @GetMapping(path = "{id}")
    public Classroom getClassroomById(@PathVariable("id") String id)  {
        return classroomService.getClassroomById(id).orElse(null);
    }

    @GetMapping(path = "/distribution")
    public Pair<Integer, Integer> getClassroomDistributionById(@NotNull @RequestParam("id") String id) { return classroomService.getClassroomDistributionById(id); }

    @DeleteMapping(path = "{id}")
    public void deleteClassroomById(@PathVariable("id") String id, @NotNull @RequestParam("adminID") String adminID) {
        classroomService.deleteClassroomByID(id, adminID);
    }

    @PutMapping
    public Classroom updateClassroom(@NotNull @RequestParam("id") String id, @NotNull @RequestParam("capacity") int capacity,
                                     @RequestParam("name") String name, @NotNull @RequestParam("numRows") int numRows,
                                     @NotNull @RequestParam("numCols") int numCols) {
        return classroomService.updateClassroom(id, capacity, name, numRows, numCols);
    }

}
