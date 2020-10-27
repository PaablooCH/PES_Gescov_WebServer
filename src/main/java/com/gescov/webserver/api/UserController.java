package com.gescov.webserver.api;

import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
import com.gescov.webserver.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@NonNull @RequestBody User user) {
        List<School> schools = user.getSchools();
        Set<String> notRepeated = new HashSet<>();
        for (School sc : schools) {
            if (notRepeated.contains(sc.getName())) throw new AlreadyExistsException("Cannot insert the same school twice");
            notRepeated.add(sc.getName());
        }
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/school")
    public List<School> getUserSchools(@NonNull @RequestParam("id") ObjectId id) {
        return userService.getUserSchools(id);
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") ObjectId id)  {
        return userService.getUserById(id);
    }

}
