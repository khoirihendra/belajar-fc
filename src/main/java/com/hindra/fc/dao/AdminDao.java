package com.hindra.fc.dao;

import com.hindra.fc.model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository <Admin, String> {

	Admin findByUserid(String userid);
    
}
