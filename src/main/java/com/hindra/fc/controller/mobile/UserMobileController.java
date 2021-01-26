package com.hindra.fc.controller.mobile;

import java.net.http.HttpHeaders;

import com.hindra.fc.model.Admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mobile")
public class UserMobileController {
    
    @PostMapping(path = "/login", produces = "application/json")
    private ResponseEntity<String> login (
        @RequestHeader HttpHeaders header,
        @RequestBody(required = true) Admin admin
    ) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("");
    }
}
