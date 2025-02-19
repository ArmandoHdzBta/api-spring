package com.nando.apiExample.controller;

import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;
import com.nando.apiExample.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends Controller {
    public UserService userServise;

    public UserController(UserService userServise) {
        this.userServise = userServise;
    }

    @GetMapping("/user/{id}")
    public User getAllUsers(@PathVariable Integer id) {
        return this.userServise.getUserById(id);
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody UserDto user) {
        return this.userServise.save(user);
    }
}
