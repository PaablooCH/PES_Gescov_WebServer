package com.gescov.webserver.api;

import com.gescov.webserver.model.ClassSession;
import com.gescov.webserver.service.ClassSessionService;
import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<ClassSession> getAllSubject(){
        return sessionService.getAllSessions();
    }


    @DeleteMapping
    public void deleteSubject(@NonNull @RequestParam("id")ObjectId id){
        sessionService.deleteSession(id);
    }

    @PutMapping
    public void updateSubject(@NonNull @RequestParam ("id") ObjectId id, @NonNull @RequestBody ClassSession session){
        sessionService.updateSubject(id, session);
    }
}
