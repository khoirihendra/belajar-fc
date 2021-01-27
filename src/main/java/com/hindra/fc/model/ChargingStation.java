package com.hindra.fc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "m_charging_station")
public class ChargingStation {
    
    @Id
    @Column(name = "station_id")
    private String stationid;

    @Column(name = "station_name", nullable = false)
    private String stationname;

    @Column(name = "station_code", nullable = false, unique = true)
    private String stationcode;

    @Column(name = "plug_type")
    private String plugtype;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "is_available")
    private String isavailable;

    @Column(name = "is_active")
    private String isactive;

    @Column(name = "created_at")
    private Date createdat;

    @Column(name = "created_by")
    private String createdby;

    @Column(name = "updated_at")
    private Date updatedat;

    @Column(name = "updated_by")
    private String updatedby;
}
