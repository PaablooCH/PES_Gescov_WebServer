package com.gescov.webserver.api;


import com.gescov.webserver.model.Assignment;
import com.gescov.webserver.service.AssignmentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/assignment")
@RestController
public class AssignmentController {
    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public void addAssignment(@NonNull @RequestBody Assignment assignment) {
        assignmentService.addAssignment(assignment);
    }

    /*@GetMapping
    public List<Assignment> getAllAssignment() { return assignmentService.getAllAssignment(); }*/

    @GetMapping
    public List<Assignment> getAssignmentByClassId(@RequestParam("id") ObjectId id) {
        return assignmentService.getAssignmentByClassId(id);
    }

    @GetMapping(path = "/classDate")
    public List<Assignment> getAssignmentByClassroomDate(@RequestParam("class") ObjectId idClassroom, @RequestParam("date") String date,
                                                         @RequestParam("date") String hour) {
        return assignmentService.getAssignmentByClassroomDate(idClassroom, date, hour);
    }

    /*@GetMapping(path = "/school")
    public List<Assignment> getAssignmentBySchool(@RequestParam("name") String nameCen) {
        return assignmentService.getAssignmentBySchool(nameCen);
    }*/

    @GetMapping(path = "/classroom")
    public List<Assignment> getAssignmentByClassroom(@RequestParam("name") String nameClassroom) {
        return assignmentService.getAssignmentByClassroom(nameClassroom);
    }

    @PutMapping
    public void updateAssignment(@RequestParam("id") ObjectId id, @RequestParam("row") int posRow,
                                 @RequestParam("col") int posCol) {
        assignmentService.updateAssignment(id, posRow, posCol);
    }

}