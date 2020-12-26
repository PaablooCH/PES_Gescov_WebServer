package com.gescov.webserver.service;

import com.gescov.webserver.dao.wall.WallEntryDao;
import com.gescov.webserver.exception.NotAnyTextException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
import com.gescov.webserver.model.WallEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class WallEntryService {

    @Autowired
    WallEntryDao wallEntryDao;

    @Autowired
    SchoolService schoolService;

    @Autowired
    UserService userService;

    public WallEntry addEntry(WallEntry entry, String creatorID){
        if(entry.getText().equals("")) throw new NotAnyTextException();
        School sch = schoolService.getSchoolByID(entry.getSchoolID());
        User u = userService.getUserById(creatorID);
        schoolService.isAdmin(sch.getId(),u.getId());
        return wallEntryDao.insert(entry);
    }

    public List<WallEntry> getAllEntrysOfSchool(String schoolID){
        List<WallEntry> we = wallEntryDao.getAllBySchoolID(schoolID);
        we.sort(Comparator.comparing(WallEntry::getHour));
        return we;
    }

    public void deleteEntry(String entryID){
        WallEntry we = getEntryByID(entryID);
        wallEntryDao.deleteById(we.getId());
    }

    private WallEntry getEntryByID(String entryID) {
        Optional<WallEntry> we = wallEntryDao.findById(entryID);
        if(we.isEmpty()) throw new NotFoundException(WallEntry.class, entryID);
        return we.get();
    }

    public WallEntry updateEntryText(String entryID, String newText){
        WallEntry we = getEntryByID(entryID);
        if(newText.equals(""))throw new NotAnyTextException();
        we.setText(newText);
        return wallEntryDao.save(we);
    }

}
