package com.hindra.fc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tr_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private String paymentid;

    @Column(name = "fee", nullable = false)
    private String fee;

    @Column(name = "kwh", nullable = false)
    private String kwh;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at")
    private Date createdat;

    @Column(name = "created_by")
    private String createdby;

    @Column(name = "updated_at")
    private Date updatedat;

    @Column(name = "updated_by")
    private String updatedby;
}
