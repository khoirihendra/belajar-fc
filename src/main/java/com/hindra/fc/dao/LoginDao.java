package com.hindra.fc.dao;

import com.hindra.fc.model.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends JpaRepository<Login, String> {

	Login findByUserid(String userId);

	Login findByUsername(String username);
    
}
