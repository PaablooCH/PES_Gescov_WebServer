package com.gescov.webserver.api;

import com.gescov.webserver.model.School;
import com.gescov.webserver.service.SchoolService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/school")
@RestController
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public void addSchool(@NonNull @RequestBody School school) {
        schoolService.addSchool(school);
    }

    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping(path = "/id/{id}")
    public School getSchoolById(@PathVariable("id") ObjectId id) {
        return schoolService.getSchoolById(id);
    }

    @GetMapping(path = "/name/{schoolName}")
    public School getSchoolByName(@PathVariable("schoolName") String schoolName) {
        return schoolService.getSchoolByName(schoolName);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") ObjectId id) {
        schoolService.deleteSchool(id);
    }

    @PutMapping(path = "{specific}")
    public void updateSchoolName(@PathVariable("specific") String specific, @NonNull @RequestParam("id") ObjectId id, @NonNull @RequestParam("update") String update) {
        if (specific.equals("name")) schoolService.updateSchoolName(id, update);
        else if (specific.equals("state")) schoolService.updateSchoolState(id, update);
    }

}


