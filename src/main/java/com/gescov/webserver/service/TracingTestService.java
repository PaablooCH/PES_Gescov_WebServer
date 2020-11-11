package com.gescov.webserver.service;

import com.gescov.webserver.dao.TracingTest.TracingTestDao;
import com.gescov.webserver.model.TracingTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TracingTestService {

    @Autowired
    TracingTestDao tracingTestDao;

    @Autowired
    ContagionService contagionService;

    public TracingTest addTracingTest(TracingTest tracingTest) {
        contagionService.existsContagion(tracingTest.getContagionID());
        return tracingTestDao.insert(tracingTest);
    }

    public List<TracingTest> getTracingTestByUser(String contagionID) {
        contagionService.existsContagion(contagionID);
        return tracingTestDao.findAllByContagionID(contagionID);
    }

    public void deleteAllTracingTest(String contagionID) {
        tracingTestDao.deleteAllByContagionID(contagionID);
    }
}
