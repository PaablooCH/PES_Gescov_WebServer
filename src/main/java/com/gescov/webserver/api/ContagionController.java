package com.gescov.webserver.api;

import com.gescov.webserver.model.Contagion;
import com.gescov.webserver.service.ContagionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/contagion")
@RestController
public class ContagionController {

    private final ContagionService contagionService;

    @Autowired
    public ContagionController(ContagionService contagionService) { this.contagionService = contagionService; }

    @PostMapping
    public void addContagion(@NonNull @RequestBody Contagion contagion) { contagionService.addContagion(contagion); }

    @GetMapping
    public List<Contagion> getAllContagion() {
        return contagionService.getAllContagion();
    }

    @GetMapping(path = "{specific}")
    public List<Contagion> getSpecificContagion(@PathVariable("specific") String specific) {
        List<Contagion> returned = null;
        if (specific.equals("now")) returned = contagionService.getNowContagion();
        return returned;
    }

    @PutMapping(path = "{nameInfected}")
    public void updateContagion(@PathVariable("nameInfected") String nameInfected) {
        contagionService.updateContagion(nameInfected);
    }

}
