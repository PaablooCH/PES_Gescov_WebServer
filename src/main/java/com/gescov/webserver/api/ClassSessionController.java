package com.gescov.webserver.api;

import com.gescov.webserver.model.ClassSession;
import com.gescov.webserver.service.ClassSessionService;
import com.mongodb.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/classSession")
@RestController
public class ClassSessionController {

    private final ClassSessionService sessionService;

    @Autowired
    public ClassSessionController(ClassSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public void addSession(@NonNull @RequestBody ClassSession sessions){
        sessionService.addSession(sessions);
    }

    @GetMapping
    public List<ClassSession> getAllSessions(){
        return sessionService.getAllSessions();
    }

    @GetMapping(path = "{specific}")
    public List<ClassSession> getSpecificSessions(@PathVariable("specific") String specific, @NonNull @RequestParam("name") String name){
        List<ClassSession> returned = new ArrayList<>();
        if(specific.equals("classroom")) returned = sessionService.getSessionByClassroom(name);
        if(specific.equals("subject")) returned = sessionService.getSessionBySubject(name);
        if(specific.equals("teacher")) returned = sessionService.getSessionByTeacher(name);
        if(specific.equals("hour")) returned = sessionService.getSessionByHour(name);
        if(specific.equals("date")) returned = sessionService.getSessionByDate(name);
        return returned;
    }

    @DeleteMapping
    public void deleteSubject(@NonNull @RequestParam("id")String id){
        sessionService.deleteSession(id);
    }
/*
    @PutMapping
    public void updateSubject(@NonNull @RequestParam ("id") ObjectId id, @NonNull @RequestBody ClassSession session){
        sessionService.updateSubject(id, session);
    }
 */
}
