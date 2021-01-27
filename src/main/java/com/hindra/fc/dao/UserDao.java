package com.hindra.fc.dao;

import com.hindra.fc.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository <User, String> {

	User findByUserid(String userid);
    
}
