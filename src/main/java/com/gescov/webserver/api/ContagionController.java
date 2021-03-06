package com.gescov.webserver.api;

import com.gescov.webserver.model.Contagion;
import com.gescov.webserver.service.ContagionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("api/contagions")
@RestController
public class ContagionController {

    private final ContagionService contagionService;

    @Autowired
    public ContagionController(ContagionService contagionService) {
        this.contagionService = contagionService;
    }

    @PostMapping
    public Contagion addContagion(@NonNull @RequestBody Contagion contagion) { return contagionService.addContagion(contagion); }

    @GetMapping
    public List<Contagion> getAllContagion() {
        return contagionService.getAllContagion();
    }

    @GetMapping(path = "/now/{schoolID}")
    public List<Pair<String, LocalDate>> getContagionNow(@PathVariable("schoolID") String schoolID) {
        return contagionService.getNowContagion(schoolID);
    }

    @PutMapping(path = "/{id}")
    public void updateContagion(@PathVariable("id") String infectedID) {
        contagionService.updateContagion(infectedID);
    }

    @GetMapping(path = "/student/{id}")
    public String getUserContagionID(@PathVariable("id") String infectedID) {
        return contagionService.getContagionByUser(infectedID).getId();
    }

}
