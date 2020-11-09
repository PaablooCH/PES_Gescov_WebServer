package com.gescov.webserver.api;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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

    @GetMapping(path = "{id}")
    public Classroom getClassroomById(@PathVariable("id") String id)  {
        return classroomService.getClassroomById(id).orElse(null);
    }

    @GetMapping(path = "/distribution")
    public Pair<Integer, Integer> getClassroomDistributionById(@NotNull @RequestParam("id") String id) { return classroomService.getClassroomDistributionById(id); }

    @DeleteMapping(path = "{id}")
    public void deleteClassroomById(@PathVariable("id") String id) {
        classroomService.deleteClassroom(id);
    }

    @PutMapping
    public void updateClassroom(@NotNull @RequestParam("id") String id, @NotNull @RequestParam("name") String name) {
        classroomService.updateClassroomName(id, name);
    }

    @PutMapping(path = "{specific}")
    public void updateClassroom(@PathVariable("specific") String specific, @NotNull @RequestParam("id") String id,
                                @NotNull @RequestParam("update") int update) {
        if (specific.equals("capacity")) classroomService.updateClassroomCapacity(id, update);
        else if (specific.equals("numRows")) classroomService.updateClassroomNumRows(id, update);
        else if (specific.equals("numCols")) classroomService.updateClassroomNumCols(id, update);
    }

}
