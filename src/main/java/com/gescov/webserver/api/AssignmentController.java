package com.gescov.webserver.api;


import com.gescov.webserver.model.Assignment;
import com.gescov.webserver.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/assignments")
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

    @GetMapping(path = "/classSession/{id}")
    public List<Assignment> getAssignmentByClassId(@PathVariable("id") String classSessionID) {
        return assignmentService.getAssignmentByClassSessionId(classSessionID);
    }

    @GetMapping(path = "/classDate")
    public List<Pair<Assignment, String>> getAssignmentByClassroomDate(@RequestParam("classroomID") String idClassroom, @RequestParam("date") String date) {
        return assignmentService.getAssignmentByClassroomDate(idClassroom, date);
    }

    @GetMapping(path = "/classDateHour")
    public List<Pair<Assignment, String>> getAssignmentByClassroomDateHour(@RequestParam("classroomID") String idClassroom, @RequestParam("date") String date, @RequestParam("hour") String hour) {
        return assignmentService.getAssignmentByClassroomDateHour(idClassroom, date, hour);
    }

    @GetMapping(path = "/classroom/{id}")
    public List<Pair<Assignment, String>> getAssignmentByClassroom(@PathVariable("id") String classroomID) {
        return assignmentService.getAssignmentsByClassroom(classroomID);
    }

    @PutMapping(path = "/{id}")
    public void updateAssignment(@PathVariable("id") String id, @RequestParam("row") int posRow,
                                 @RequestParam("col") int posCol) {
        assignmentService.updateAssignment(id, posRow, posCol);
    }

}