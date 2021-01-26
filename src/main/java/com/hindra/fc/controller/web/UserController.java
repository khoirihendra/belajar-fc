package com.hindra.fc.controller.web;

import java.util.Map;

import com.hindra.fc.model.Admin;
import com.hindra.fc.model.Login;
import com.hindra.fc.service.web.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/web")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register-admin", produces = "application/json")
    public ResponseEntity<Map<String, String>> registerAdmin(@RequestBody Login login) {
        
        return userService.register(login);
    }

    @PostMapping(path = "/login-admin", produces = "application/json")
    public ResponseEntity<Map<String, ?>> loginAdmin(@RequestBody Login login) {
        
        return userService.login(login);
    }
    
    @GetMapping(path = "/profile/{userid}", produces = "application/json")
    public ResponseEntity<Admin> getProfile(@PathVariable(required = true) String userid) {
        
        return userService.getProfile(userid);
    }
}