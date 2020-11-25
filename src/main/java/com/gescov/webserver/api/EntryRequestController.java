package com.gescov.webserver.api;

import com.gescov.webserver.model.EntryRequest;
import com.gescov.webserver.service.EntryRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/entryRequests")
@RestController
public class EntryRequestController {

    private final EntryRequestService entryRequestService;

    @Autowired
    public EntryRequestController(EntryRequestService entryRequestService) {
        this.entryRequestService = entryRequestService;
    }

    @PostMapping
    public EntryRequest addSchool(@RequestBody EntryRequest entryRequest) {
        return entryRequestService.addEntryRequest(entryRequest);
    }

    @GetMapping(path = "/schoolID/{schoolID}")
    public List<EntryRequest> getRequestsBySchool(@PathVariable("schoolID") String schoolID) {
        return entryRequestService.getRequestsBySchool(schoolID);
    }

    @GetMapping(path = "/userID/{userID}")
    public List<EntryRequest> getRequestsByUser(@PathVariable("userID") String userID) {
        return entryRequestService.getRequestsByUser(userID);
    }

    @PutMapping(path = "/requestID/{requestID}")
    public void updateEntryRequest(@PathVariable("requestID") String requestID, @NotNull @RequestParam("state") String state) {
        entryRequestService.updateRequestState(requestID, state);
    }

}
