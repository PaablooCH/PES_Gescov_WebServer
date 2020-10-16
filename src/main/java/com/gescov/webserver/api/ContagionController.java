package com.gescov.webserver.api;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.Contagion;
import com.gescov.webserver.service.ContagionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/contagion")
@RestController
public class ContagionController {

    private final ContagionService contagionService;

    @Autowired
    public ContagionController(ContagionService classroomService) { this.contagionService = classroomService; }

    @PostMapping
    public void addClassroom(@NonNull @RequestBody Contagion contagion) {
        contagionService.addContagion(contagion);
    }

}
