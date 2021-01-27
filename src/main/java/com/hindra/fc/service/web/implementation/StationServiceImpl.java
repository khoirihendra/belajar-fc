package com.hindra.fc.service.web.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.hindra.fc.dao.ChargingStationDao;
import com.hindra.fc.model.ChargingStation;
import com.hindra.fc.service.web.StationService;
import com.hindra.fc.util.JwtTokenUtil;
import com.hindra.fc.util.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private ChargingStationDao chargingStationDao;

    @Autowired
    private JwtTokenUtil jwtToken;

    @Override
    public ResponseEntity<?> listChargingStation() {

        Boolean status = false;
        String msg = "";
        List<ChargingStation> data = new ArrayList<>();

        try {

            data = chargingStationDao.findAll();

            status = true;
            msg = "List of charging stations.";
            
        } catch (Exception e) {
            msg = e.getMessage();
        }
        
        Response<List<ChargingStation>> res = new Response<List<ChargingStation>>(status, msg, data);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }

    @Override
    public ResponseEntity<?> addChargingStations(ChargingStation chargingStation, String token) {
        Boolean status = false;
        String msg = "";
        
        System.out.println("asd");

        try {
            String userid = jwtToken.getUserIdFromToken(token);
            String stationId = UUID.randomUUID().toString();

            // add charging station
            chargingStation.setStationid(stationId);
            chargingStation.setCreatedat(new Date());
            chargingStation.setCreatedby(userid);
            chargingStationDao.save(chargingStation);

            status = true;
            msg = "New charging station has been created.";

        } catch (Exception e) {
            msg = e.getMessage();
        }

        Response<ChargingStation> res = new Response<ChargingStation>(status, msg, chargingStation);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }
}
