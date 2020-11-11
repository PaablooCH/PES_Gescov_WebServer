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
    private final TracingTestController tracingTestController;

    @Autowired
    public ContagionController(ContagionService contagionService, TracingTestController tracingTestController) {
        this.contagionService = contagionService;
        this.tracingTestController = tracingTestController;
    }

    @PostMapping
    public Contagion addContagion(@NonNull @RequestBody Contagion contagion) { return contagionService.addContagion(contagion); }

    @GetMapping
    public List<Contagion> getAllContagion() {
        return contagionService.getAllContagion();
    }

    /*@GetMapping(path = "{specific}")
    public List<Contagion> getSpecificContagion(@PathVariable("specific") String specific, @RequestParam("nameCen") String nameCen) {
        List<Contagion> returned = null;
        if (specific.equals("now")) returned = contagionService.getNowContagion(nameCen);
        return returned;
    }*/

    @GetMapping(path = "/now")
    public List<Contagion> getContagionNow(@RequestParam("schoolID") String schoolID) {
        return contagionService.getNowContagion(schoolID);
    }

    @PutMapping
    public void updateContagion(@RequestParam("infectedID") String infectedID) {
        contagionService.updateContagion(infectedID);
        tracingTestController.deleteAllTracingTest(infectedID);
    }

}
