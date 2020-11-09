package com.gescov.webserver.api;


import com.gescov.webserver.model.Subject;
import com.gescov.webserver.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/subject")
@RestController
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) { this.subjectService = subjectService; }

    @PostMapping
    public Subject addSubject(@NotNull @RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    @GetMapping
    public List<Subject> getAllSubject(){
        return subjectService.getAllSubject();
    }

    @GetMapping(path = "/id")
    public Optional<Subject> getSubjectById(@NotNull @RequestParam("id") String id) { return subjectService.findById(id); }

    @GetMapping(path = "/schools/{specific}")
    public List<Subject> getSubjectBySchoolId(@PathVariable("specific") String id) { return subjectService.selectSubjectBySchoolId(id); }

    @GetMapping(path = "{specific}")
    public List<Subject> getSubjectsBySchool(@PathVariable("specific") String specific, @NotNull @RequestParam("name") String name){
        List<Subject> returned = new ArrayList<>();
        if(specific.equals("schools")) returned =  subjectService.getSubjectBySchool(name);
        if(specific.equals("names")) returned = subjectService.getSubjectByName(name);
        return returned;
    }

    @DeleteMapping
    public void deleteSubject(@NotNull @RequestParam("id") String id){
        subjectService.deleteSubject(id);
    }

    @PutMapping
    public void updateSubject(@NotNull @RequestParam ("id") String id, @NotNull @RequestBody String name){
        subjectService.updateSubject(id, name);
    }

    @PutMapping(path = "/new/{specific}")
    public void addStudents(@NotNull @PathVariable ("specific") String specific, @NotNull @RequestParam("id") String id, @NotNull @RequestParam ("userId") String userId){
        if(specific.equals("student")) subjectService.addStudent(id, userId);
        if(specific.equals("teacher")) subjectService.addTeacher(id, userId);
    }
}















