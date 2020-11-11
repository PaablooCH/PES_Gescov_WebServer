package com.gescov.webserver.service;

import com.gescov.webserver.dao.TracingTest.TracingTestDao;
import com.gescov.webserver.model.TracingTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TracingTestService {

    private final TracingTestDao tracingTestDao;
    private final ContagionService contagionService;

    @Autowired
    public TracingTestService(TracingTestDao tracingTestDao, ContagionService userService) {
        this.tracingTestDao = tracingTestDao;
        this.contagionService = userService;
    }

    public TracingTest addTracingTest(TracingTest tracingTest) {
        contagionService.existsContagion(tracingTest.getUserID());
        return tracingTestDao.insert(tracingTest);
    }

    public List<TracingTest> getTracingTestByUser(String userID) {
        contagionService.existsContagion(userID);
        return tracingTestDao.findAllByUserID(userID);
    }

    public void deleteAllTracingTest(String userID) {
        tracingTestDao.deleteAllByUserID(userID);
    }
}
