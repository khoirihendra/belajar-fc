package com.hindra.fc.controller.mobile;

import com.hindra.fc.model.Login;
import com.hindra.fc.service.mobile.UserServiceMobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mobile")
public class UserMobileController {

    @Autowired
    private UserServiceMobile userService;
    
    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody Login login) {
        
        return userService.register(login);
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody Login login) {
        
        return userService.login(login);
    }
    
    @GetMapping(path = "/profile", produces = "application/json")
    public ResponseEntity<?> getProfile(@RequestHeader HttpHeaders headers) {

        String token = headers.getFirst(HttpHeaders.AUTHORIZATION).substring(7);

        return userService.getProfile(token);
    }
}
