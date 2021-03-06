package com.gescov.webserver.api;

import com.gescov.webserver.model.WallEntry;
import com.gescov.webserver.model.WallEntryReply;
import com.gescov.webserver.service.WallEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/wallEntry")
@RestController
public class WallEntryController {

    private final WallEntryService wallEntryService;

    @Autowired
    public WallEntryController(WallEntryService wallEntryService){ this.wallEntryService = wallEntryService;}

    @PostMapping
    public WallEntry addEntry(@NotNull @RequestBody WallEntry wallEntry, @NotNull @RequestParam ("creatorID") String creatorID) {
        return wallEntryService.addEntry(wallEntry, creatorID);
    }

    @GetMapping(path = "{id}")
    public List<WallEntry> getAllEntryofSchool(@NotNull @PathVariable ("id")String id) {
        return wallEntryService.getAllEntrysOfSchool(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteWallEntryByID(@NotNull @PathVariable ("id")String id, @NotNull @RequestParam ("userID") String userID) {
        wallEntryService.deleteEntry(id, userID);
    }

    @PutMapping(path = "{id}")
    public WallEntry updateWallEntryText(@NotNull @PathVariable("id") String id, @NotNull @RequestParam ("text") String text) {
        return wallEntryService.updateEntryText(id, text);
    }

    @PostMapping(path = "{id}/reply")
    public WallEntry addReply(@NotNull @PathVariable ("id") String id, @NotNull @RequestBody WallEntryReply rep,
                              @NotNull @RequestParam ("userID") String userID) {
        return wallEntryService.addReply(id,rep,userID);
    }

}
