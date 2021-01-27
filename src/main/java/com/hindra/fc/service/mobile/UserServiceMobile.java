package com.hindra.fc.service.mobile;

import com.hindra.fc.model.Login;

import org.springframework.http.ResponseEntity;

public interface UserServiceMobile {
    ResponseEntity<?> register(Login login);
    ResponseEntity<?> login(Login login);
    ResponseEntity<?> getProfile(String token);
}
