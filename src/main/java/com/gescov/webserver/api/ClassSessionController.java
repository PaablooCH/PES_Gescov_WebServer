package com.gescov.webserver.api;

import com.gescov.webserver.model.ClassSession;
import com.gescov.webserver.service.ClassSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/classSessions")
@RestController
public class ClassSessionController {

    private final ClassSessionService sessionService;

    @Autowired
    public ClassSessionController(ClassSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ClassSession addSession(@NotNull @RequestBody ClassSession sessions){
        return sessionService.addSession(sessions);
    }

    @GetMapping
    public List<ClassSession> getAllSessions(){
        return sessionService.getAllSessions();
    }

    @GetMapping(path = "/subject/{id}")
    public List<Pair<ClassSession, Pair<String, String>>> getSessionBySubject(@PathVariable("id") String id){
        return sessionService.getSessionInfoBySubject(id);
    }

    @GetMapping(path = "/classroom/{id}")
    public List<ClassSession> getSessionByClassroom(@PathVariable("id") String id){
        return sessionService.getSessionByClassroom(id);
    }

    @GetMapping(path = "/teacher/{id}")
    public List<ClassSession> getSessionByTeacher(@PathVariable("id") String id){
        return sessionService.getSessionByTeacher(id);
    }

    @DeleteMapping
    public void deleteClassSession(@NotNull @RequestParam ("usuID") String usuID, @NotNull @RequestParam("id") String id){
        sessionService.deleteClassSessionById(usuID, id);
    }
}
