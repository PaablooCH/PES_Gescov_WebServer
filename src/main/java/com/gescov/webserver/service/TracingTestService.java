package com.gescov.webserver.service;

import com.gescov.webserver.dao.tracingTest.TracingTestDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Contagion;
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
        if (!contagionService.existsContagion(tracingTest.getContagionID()))
            throw new NotFoundException(Contagion.class, tracingTest.getContagionID());
        return tracingTestDao.insert(tracingTest);
    }

    public List<TracingTest> getTracingTestByUser(String userID) {
        Contagion contagion = contagionService.getContagionByUser(userID);
        return tracingTestDao.findAllByContagionID(contagion.getId());
    }

    public void deleteAllTracingTest(String contagionID) {
        tracingTestDao.deleteAllByContagionID(contagionID);
    }

}
