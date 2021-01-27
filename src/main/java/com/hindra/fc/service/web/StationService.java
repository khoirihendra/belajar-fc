package com.hindra.fc.service.web;

import com.hindra.fc.model.ChargingStation;

import org.springframework.http.ResponseEntity;

public interface StationService {
    
    ResponseEntity<?> listChargingStation();
    ResponseEntity<?> addChargingStations(ChargingStation chargingStation, String token);
}
