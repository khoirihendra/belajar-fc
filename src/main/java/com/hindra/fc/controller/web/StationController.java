package com.hindra.fc.controller.web;

import com.hindra.fc.model.ChargingStation;
import com.hindra.fc.service.web.StationService;

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
@RequestMapping("/web")
public class StationController {

    @Autowired
    private StationService stationService;
    
    @GetMapping(path = "/charging-stations", produces = "application/json")
    public ResponseEntity<?> getListChargingStations() {

        return stationService.listChargingStation();
    }

    @PostMapping(path = "/charging-stations/add", produces = "application/json")
    public ResponseEntity<?> addChargingStations(
        @RequestHeader HttpHeaders headers,
        @RequestBody ChargingStation chargingStation) {

        String token = headers.getFirst(HttpHeaders.AUTHORIZATION).substring(7);
        
        return stationService.addChargingStations(chargingStation, token);
    }
}
