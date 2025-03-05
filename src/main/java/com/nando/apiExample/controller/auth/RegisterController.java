package com.nando.apiExample.controller.auth;

import com.nando.apiExample.controller.Controller;
import com.nando.apiExample.controller.http.DataResponse;
import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.service.auth.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RegisterController extends Controller {
    private final RegisterService registerService;

    RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<DataResponse<UserDto>> saveUser(@RequestBody UserDto user) {
        try {
            DataResponse<UserDto> response = new DataResponse<>("User created", this.registerService.register(user));
            return new ResponseEntity<>(response, HttpStatus.CREATED) ;
        } catch(ResponseStatusException ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
