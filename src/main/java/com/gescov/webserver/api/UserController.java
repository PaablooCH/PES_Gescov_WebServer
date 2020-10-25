package com.gescov.webserver.api;

import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
import com.gescov.webserver.service.ClassroomService;
import com.gescov.webserver.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/school")
    public School getUserSchool(@NonNull @RequestParam("id") ObjectId id) {
        return userService.getUserSchool(id);
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") ObjectId id)  {
        return userService.getUserById(id);
    }

}
