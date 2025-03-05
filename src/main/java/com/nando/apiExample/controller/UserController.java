package com.nando.apiExample.controller;

import com.nando.apiExample.controller.http.DataResponse;
import com.nando.apiExample.controller.http.SimpleResponse;
import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;
import com.nando.apiExample.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController extends Controller {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            DataResponse<List<UserDto>> response = new DataResponse<>("User list", this.userService.findAll());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        try {
            UserDto user = this.userService.getUserById(id);

            if(user == null) {
                return new ResponseEntity<>(new SimpleResponse("User not found"), HttpStatus.BAD_REQUEST);
            }

            DataResponse<UserDto> response = new DataResponse<>("User find", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
