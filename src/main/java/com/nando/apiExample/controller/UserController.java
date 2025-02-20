package com.nando.apiExample.controller;

import com.nando.apiExample.controller.http.DataResponse;
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
    public ResponseEntity<DataResponse<List<UserDto>>> getAllUsers() {
        try {
            DataResponse<List<UserDto>> response = new DataResponse<>("User list", this.userService.findAll());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<DataResponse<UserDto>> getUser(@PathVariable Integer id) {
        try {
            DataResponse<UserDto> response = new DataResponse<>("User find", this.userService.getUserById(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<DataResponse<UserDto>> saveUser(@RequestBody UserDto user) {
        try {
            DataResponse<UserDto> response = new DataResponse<>("User created", this.userService.save(user));
            return new ResponseEntity<>(response, HttpStatus.CREATED) ;
        } catch(ResponseStatusException ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
