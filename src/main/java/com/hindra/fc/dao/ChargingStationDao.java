package com.hindra.fc.dao;

import com.hindra.fc.model.ChargingStation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingStationDao extends JpaRepository <ChargingStation, String>{
    
}
