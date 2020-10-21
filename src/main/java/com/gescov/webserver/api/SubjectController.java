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

    @GetMapping(path = "{specific}")
    public List<Subject> getSubjectsByVariable(@PathVariable("specific") String specific){
        return subjectService.getSubjectByVariable(specific);
    }

    @DeleteMapping(path = "{name}")
    public void deleteSubject(@PathVariable("name") String name){
        subjectService.deleteSubject(name);
    }

    @PutMapping(path = "{name}")
    public void updateSubject(@PathVariable ("name") String name, @NonNull @RequestBody Subject subject){
        subjectService.updateSubject(name, subject);
    }
}
