package com.hindra.fc.dao;

import com.hindra.fc.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends JpaRepository <Payment, String> {
    
}
