package com.hindra.fc.controller.web;

import java.util.List;

import com.hindra.fc.model.ChargingStation;
import com.hindra.fc.service.web.StationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class StationController {

    @Autowired
    private StationService stationService;
    
    @GetMapping(path = "/charging-stations", produces = "application/json")
    public ResponseEntity<List<ChargingStation>> getListChargingStations() {

        return stationService.listChargingStation();
    }
}
