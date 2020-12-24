package com.gescov.webserver.service;

import com.gescov.webserver.dao.wall.WallEntryDao;
import com.gescov.webserver.exception.IncorrectEntryCodeException;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
import com.gescov.webserver.model.WallEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WallEntryService {

    @Autowired
    WallEntryDao wallEntryDao;

    @Autowired
    SchoolService schoolService;

    @Autowired
    UserService userService;

    public WallEntry addEntry(WallEntry entry, String creatorID){
        if(entry.getText().equals("")) throw new IncorrectEntryCodeException(entry.getSchoolID(), entry.getText());
        School sch = schoolService.getSchoolByID(entry.getSchoolID());
        User u = userService.getUserById(creatorID);
        schoolService.isAdmin(sch.getId(),creatorID);
        return wallEntryDao.insert(entry);
    }

    public List<WallEntry> getAllEntrysOfSchool(String schoolID){
        return wallEntryDao.getAllBySchoolID(schoolID);
    }
}
