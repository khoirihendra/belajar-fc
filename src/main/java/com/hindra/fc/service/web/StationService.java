package com.hindra.fc.service.web;

import java.util.List;

import com.hindra.fc.model.ChargingStation;

import org.springframework.http.ResponseEntity;

public interface StationService {
    
    ResponseEntity<List<ChargingStation>> listChargingStation();
}
