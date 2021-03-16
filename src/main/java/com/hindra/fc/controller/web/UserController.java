package com.hindra.fc.controller.web;

import java.util.Map;

import com.hindra.fc.dto.GoogleProfileDTO;
import com.hindra.fc.model.Login;
import com.hindra.fc.service.web.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@Api(tags = "API Web - Modul User")
@RequestMapping("/web")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register-admin", produces = "application/json")
    public ResponseEntity<?> registerAdmin(@RequestBody Login login) {
        
        return userService.register(login);
    }

    @PostMapping(path = "/login-admin", produces = "application/json")
    public ResponseEntity<?> loginAdmin(@RequestBody Login login) {
        
        return userService.login(login);
    }

    @PostMapping(path = "/login-google-admin", produces = "application/json")
    public ResponseEntity<?> loginGoogleAdmin(@RequestBody GoogleProfileDTO googleAccessToken) {

        // Map<String, Object> params = new JacksonJsonParser().parseMap(param);
		// String googleAccessToken = (String) params.get("accessToken");

        return userService.loginGoogle(googleAccessToken);
    }
    
    @GetMapping(path = "/profile/{userid}", produces = "application/json")
    public ResponseEntity<?> getProfile(@PathVariable(required = true) String userid) {
        
        return userService.getProfile(userid);
    }

    @GetMapping(path = "/users", produces = "application/json")
    public ResponseEntity<?> listMobileUsers() {
        
        return userService.listMobileUsers();
    }
}
