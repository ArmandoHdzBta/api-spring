package com.nando.apiExample.controller.auth;

import com.nando.apiExample.controller.Controller;
import com.nando.apiExample.controller.http.DataResponse;
import com.nando.apiExample.controller.http.SimpleResponse;
import com.nando.apiExample.model.dto.AuthDto;
import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.service.auth.AuthService;
import com.nando.apiExample.service.tokens.GenerateJwtToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final GenerateJwtToken generateJwtToken;

    AuthController(final AuthService authService, final GenerateJwtToken generateJwtToken) {
        this.authService = authService;
        this.generateJwtToken = generateJwtToken;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto request){
        try {
            UserDto login = this.authService.login(request);

            if(login == null){
                SimpleResponse userNotFound = new SimpleResponse("User not found");
                return new ResponseEntity<>(userNotFound, HttpStatus.BAD_REQUEST);
            }

            Map<String, Object> jwtToken = new HashMap<>();

            jwtToken.put("token", this.generateJwtToken.generateJwtToken(login));
            //jwtToken.put("refresh_token", this.generateJwtToken.generateRefreshJwtToken(login));

            Map<String, Object> data = new HashMap<>();
            data.put("token", jwtToken);

            DataResponse<?> response = new DataResponse<>("Login successfully", data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
