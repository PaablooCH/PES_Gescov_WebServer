package com.gescov.webserver.api;


import com.gescov.webserver.exception.NotFoundException;
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

    @GetMapping(path = "{option}")
    public List<Subject> getSubjectsByVariable(@PathVariable ("option") String option, @NonNull @RequestParam("nombre") String nombre){
        if(option.equals("schools") || option.equals("names"))return subjectService.getSubjectByVariable(nombre);
        else throw new NotFoundException("The field you're searching doesn't exists");
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
