package com.gescov.webserver.api;


import com.gescov.webserver.model.Subject;
import com.gescov.webserver.service.SubjectService;
import com.mongodb.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/subject")
@RestController
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping
    public Subject addSubject(@NonNull @RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    @GetMapping
    public List<Subject> getAllSubject(){
        return subjectService.getAllSubject();
    }

    @GetMapping(path = "/id")
    public Optional<Subject> getSubjectById(@NonNull @RequestParam("id") String id) { return subjectService.findById(id); }

    @GetMapping(path = "{specific}")
    public List<Subject> getSubjectsBySchool(@PathVariable("specific") String specific, @NonNull @RequestParam("name") String nombre){
        List<Subject> returned = new ArrayList<>();
        if(specific.equals("schools")) returned =  subjectService.getSubjectBySchool(nombre);
        if(specific.equals("names")) returned = subjectService.getSubjectByName(nombre);
        return returned;
    }

    @DeleteMapping
    public void deleteSubject(@NonNull @RequestParam("id") String id){
        subjectService.deleteSubject(id);
    }

    @PutMapping
    public void updateSubject(@NonNull @RequestParam ("id") String id, @NonNull @RequestBody String name){
        subjectService.updateSubject(id, name);
    }
}
