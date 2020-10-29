package com.gescov.webserver.api;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.service.ClassroomService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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

    @GetMapping(path = "/school/{schoolName}")
    public List<Classroom> getSchoolClassrooms(@PathVariable("schoolName") String schoolName)  {
        return classroomService.getSchoolClassrooms(schoolName);
    }

    @GetMapping(path = "{id}")
    public Classroom getClassroomById(@PathVariable("id") ObjectId id)  {
        return classroomService.getClassroomById(id);
    }

    @GetMapping(path = "/distribution")
    public Pair<Integer, Integer> getClassroomDistributionById(@NonNull @RequestParam("id") ObjectId id) { return classroomService.getClassroomDistributionById(id); }

    @DeleteMapping(path = "{id}")
    public void deleteClassroomById(@PathVariable("id") ObjectId id) {
        classroomService.deleteClassroom(id);
    }

    @PutMapping
    public void updateClassroom(@NonNull @RequestParam("id") ObjectId id, @NonNull @RequestParam("name") String name) {
        classroomService.updateClassroomName(id, name);
    }

    @PutMapping(path = "{specific}")
    public void updateClassroom(@PathVariable("specific") String specific, @NonNull @RequestParam("id") ObjectId id,
                                @NonNull @RequestParam("update") int update) {
        if (specific.equals("capacity")) classroomService.updateClassroomCapacity(id, update);
        else if (specific.equals("numRows")) classroomService.updateClassroomNumRows(id, update);
        else if (specific.equals("numCols")) classroomService.updateClassroomNumCols(id, update);
    }

}
