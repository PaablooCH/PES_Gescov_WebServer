package com.gescov.webserver.api;


import com.gescov.webserver.model.Subject;
import com.gescov.webserver.service.SubjectService;
import com.mongodb.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/subject")
@RestController
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public void addSubject(@NonNull @RequestBody Subject subject){
        subjectService.addSubject(subject);
    }

    @GetMapping
    public List<Subject> getAllSubject(){
        return subjectService.getAllSubject();
    }

    @GetMapping(path = "{specific}")
    public List<Subject> getSubjectsBySchool(@PathVariable("specific") String specific, @NonNull @RequestParam("nombre") String nombre){
        List<Subject> returned = new ArrayList<>();
        if(specific.equals("schools")) returned =  subjectService.getSubjectBySchool(nombre);
        if(specific.equals("names")) returned = subjectService.getSubjectByName(nombre);
        return returned;
    }

    @DeleteMapping
    public void deleteSubject(@NonNull @RequestParam("name") String name){
        subjectService.deleteSubject(name);
    }

    @PutMapping
    public void updateSubject(@NonNull @RequestParam ("name") String name, @NonNull @RequestBody Subject subject){
        subjectService.updateSubject(name, subject);
    }
}
