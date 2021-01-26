package com.hindra.fc.service.web.implementation;

import java.util.List;

import com.hindra.fc.dao.ChargingStationDao;
import com.hindra.fc.model.ChargingStation;
import com.hindra.fc.service.web.StationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private ChargingStationDao chargingStationDao;

    @Override
    public ResponseEntity<List<ChargingStation>> listChargingStation() {
        List<ChargingStation> listChargingStation = chargingStationDao.findAll();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(listChargingStation);
    }
}
