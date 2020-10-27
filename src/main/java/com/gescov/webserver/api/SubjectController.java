package com.gescov.webserver.api;


import com.gescov.webserver.model.Subject;
import com.gescov.webserver.service.SubjectService;
import com.mongodb.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/schools")
    public List<Subject> getSubjectsBySchool(@NonNull @RequestParam("nombre") String nombre){
        return subjectService.getSubjectBySchool(nombre);
    }

    @GetMapping("/names")
    public List<Subject> getSubjectsByName(@NonNull @RequestParam("nombre") String nombre){
        return subjectService.getSubjectByName(nombre);
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
