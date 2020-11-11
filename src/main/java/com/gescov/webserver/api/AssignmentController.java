package com.gescov.webserver.api;


import com.gescov.webserver.model.Assignment;
import com.gescov.webserver.service.AssignmentService;
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
    public Assignment addAssignment(@NonNull @RequestBody Assignment assignment) {
        return assignmentService.addAssignment(assignment);
    }

    //@GetMapping
    //public List<Assignment> getAllAssignment() { return assignmentService.getAllAssignment(); }

    @GetMapping
    public List<Assignment> getAssignmentByClassId(@RequestParam("cSeID") String classSessionID) {
        return assignmentService.getAssignmentByClassSessionId(classSessionID);
    }

    /*@GetMapping(path = "/classDate")
    public List<Assignment> getAssignmentByClassroomDate(@RequestParam("class") String idClassroom, @RequestParam("date") String date,
                                                         @RequestParam("date") String hour) {
        return assignmentService.getAssignmentByClassroomDate(idClassroom, date, hour);
    }*/

    @GetMapping(path = "/classroom")
    public List<Assignment> getAssignmentByClassroom(@RequestParam("classroomID") String classroomID) {
        return assignmentService.getAssignmentsByClassroom(classroomID);
    }

    @PutMapping
    public void updateAssignment(@RequestParam("id") String id, @RequestParam("row") int posRow,
                                 @RequestParam("col") int posCol) {
        assignmentService.updateAssignment(id, posRow, posCol);
    }

}