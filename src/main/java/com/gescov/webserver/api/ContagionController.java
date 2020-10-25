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
    public Contagion getSpecificContagion(@PathVariable("specific") String specific, @RequestParam("nameIn") String nameInfected, @RequestParam("nameCen") String nameCen) {
        Contagion returned = null;
        if (specific.equals("now")) returned = contagionService.getNowContagion(nameInfected, nameCen);
        return returned;
    }

    @PutMapping
    public void updateContagion(@RequestParam("nameInfected") String nameInfected) {
        contagionService.updateContagion(nameInfected);
    }

}
