package com.gescov.webserver.api;


import com.gescov.webserver.model.Subject;
import com.gescov.webserver.model.User;
import com.gescov.webserver.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/subjects")
@RestController
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) { this.subjectService = subjectService; }

    @PostMapping
    public Subject addSubject(@NotNull @RequestBody Subject subject, @NotNull @RequestParam("creator") String creatorID) {
        return subjectService.addSubject(subject, creatorID);
    }

    @GetMapping
    public List<Subject> getAllSubject(){
        return subjectService.getAllSubject();
    }

    @GetMapping(path = "/id")
    public Subject getSubjectById(@NotNull @RequestParam("id") String id) { return subjectService.getSubjectById(id); }

    @GetMapping(path = "{id}/users")
    public List<User> getUsersBySubjectId(@NotNull @PathVariable("id") String id) { return subjectService.selectStudentsBySubject(id); }

    @GetMapping(path = "/schools/{specific}")
    public List<Subject> getSubjectBySchoolId(@PathVariable("specific") String id) { return subjectService.selectSubjectBySchoolId(id); }

    @GetMapping(path = "{specific}")
    public List<Subject> getSubjectsBySchool(@PathVariable("specific") String specific, @NotNull @RequestParam("name") String name) {
        List<Subject> returned = new ArrayList<>();
        if (specific.equals("schools")) returned =  subjectService.getSubjectBySchool(name);
        if (specific.equals("names")) returned = subjectService.getSubjectByName(name);
        return returned;
    }

    @DeleteMapping
    public void deleteSubject(@NotNull @RequestParam("id") String id, @NotNull @RequestParam ("admindID") String adminID) {
        subjectService.deleteSubjectById(id, adminID);
    }

    @PutMapping
    public void updateSubject(@NotNull @RequestParam ("id") String id, @NotNull @RequestBody String name) {
        subjectService.updateSubject(id, name);
    }

    @PutMapping(path = "/{id}")
    public Subject enrolUser(@PathVariable("id") String id, @NotNull @RequestParam ("userID") String userID) {
        return subjectService.addUser(id, userID);
    }

    @GetMapping(path = "/user/{id}")
    public List<Pair<Subject, String>> getSubjectsByUserID(@PathVariable("id") String id) {
        return subjectService.getSubjectsByUserID(id);
    }

    @GetMapping(path = "/{id}/teachers")
    public List<User> getTeachersBySubjectID(@PathVariable("id") String id) {
        return subjectService.getTeachersBySubjectID(id);
    }

}















