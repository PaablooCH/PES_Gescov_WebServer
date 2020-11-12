package com.gescov.webserver.api;

import com.gescov.webserver.model.User;
import com.gescov.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "{token}")
    public String verifyToken(@PathVariable("token") String token) {
        return userService.verifyToken(token);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping (path = "/school/{id}")
    public void addSchoolID(@NotNull @PathVariable("id") String id, @NotNull @RequestParam("schoolID") String schoolID) {
        userService.addSchool(id, schoolID);
    }

    @GetMapping(path = "/school")
    public List<String> getSchoolsByUser(@NotNull @RequestParam("id") String id) {
        return userService.getSchoolsByUser(id);
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") String id)  {
        return userService.getUserById(id).orElse(null);
    }

    @PutMapping(path = "{id}")
    public void updateUseRisk(@PathVariable("id") String id) {
        userService.updateUserRisk(id);
    }

}
