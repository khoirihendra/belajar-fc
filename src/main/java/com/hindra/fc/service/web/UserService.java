package com.hindra.fc.service.web;

import java.util.Map;

import com.hindra.fc.model.Admin;
import com.hindra.fc.model.Login;

import org.springframework.http.ResponseEntity;

public interface UserService {
    
    ResponseEntity<Map<String, String>> register(Login login);
    ResponseEntity<Map<String, ?>> login(Login login);
    ResponseEntity<Admin> getProfile(String userid);
}
