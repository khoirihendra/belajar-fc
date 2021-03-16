package com.hindra.fc.service.web;

import com.hindra.fc.dto.GoogleProfileDTO;
import com.hindra.fc.model.Login;

import org.springframework.http.ResponseEntity;

public interface UserService {
    
    ResponseEntity<?> register(Login login);
    ResponseEntity<?> login(Login login);
    ResponseEntity<?> loginGoogle(GoogleProfileDTO googleAccessToken);
    ResponseEntity<?> getProfile(String userid);
    ResponseEntity<?> listMobileUsers();
}
