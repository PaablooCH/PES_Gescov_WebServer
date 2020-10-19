package com.gescov.webserver.api;


import com.gescov.webserver.model.Subject;
import com.gescov.webserver.service.SubjectService;
import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
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

    @GetMapping(path = "{id}")
    public Subject getSubjectById(@PathVariable("id") ObjectId id)throws Exception{
        return subjectService.selectSubjectByID(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSubject(@PathVariable("id") ObjectId id){
        subjectService.deleteSubject(id);
    }

    @PutMapping(path = "{id}")
    public void updateSubject(@PathVariable ("id") ObjectId id, @NonNull @RequestBody Subject subject){
        subjectService.updateSubject(id, subject);
    }
}
