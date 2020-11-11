package com.gescov.webserver.api;

import com.gescov.webserver.model.TracingTest;
import com.gescov.webserver.service.TracingTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/tracingTest")
@RestController
public class TracingTestController {

    private final TracingTestService tracingTestService;

    @Autowired
    public TracingTestController(TracingTestService tracingTestService) {
        this.tracingTestService = tracingTestService;
    }

    @PostMapping
    public TracingTest addTracingTest(@NonNull @RequestBody TracingTest tracingTest) {
        return tracingTestService.addTracingTest(tracingTest);
    }

    @GetMapping
    public List<TracingTest> getTracingTestByUser(@NonNull @RequestParam("userID") String userID) {
        return tracingTestService.getTracingTestByUser(userID);
    }

    public void deleteAllTracingTest(String userID) {
        tracingTestService.deleteAllTracingTest(userID);
    }
}
