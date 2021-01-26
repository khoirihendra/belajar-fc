package com.hindra.fc.service.web.implementation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.hindra.fc.dao.AdminDao;
import com.hindra.fc.dao.LoginDao;
import com.hindra.fc.model.Admin;
import com.hindra.fc.model.Login;
import com.hindra.fc.service.web.UserService;
import com.hindra.fc.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.MediaType;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private JwtTokenUtil jwt;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<Map<String, String>> register(Login login) {
        
        String uuid = UUID.randomUUID().toString();

        //generate token
        String token = jwt.generateToken(login);
        
        //add credential
        login.setUserid(uuid);
        login.setPassword(bCryptPasswordEncoder.encode(login.getPassword()));
        login.setToken(token);
        login.setCreatedat(new Date());
        login.setCreatedby(uuid);
        loginDao.save(login);

        //add admin detail
        Admin admin = new Admin();
        admin.setUserid(uuid);
        admin.setFullname(login.getFullname());
        admin.setCreatedat(new Date());
        admin.setCreatedby(uuid);
        adminDao.save(admin);

        Map<String, String> data = new HashMap<String, String>();
        data.put("token", token);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(data);
    }

    @Override
    public ResponseEntity<Map<String, ?>> login(Login login) {
        
        //authenticate
        Login user = loginDao.findByUsername(login.getUsername());
        Boolean auth = bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword());

        Map<String, Object> data = new HashMap<String, Object>();
        if(auth) {
            //generate token
            String token = jwt.generateToken(user);

            //update last login
            user.setToken(token);
            user.setUpdatedat(new Date());
            user.setUpdatedby(user.getUserid());
            loginDao.save(user);

            data.put("status", true);
            data.put("token", token);
        }
        else {
            data.put("status", false);
            data.put("status", "Wrong username or password!");
            data.put("token", "");
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(data);
    }

    @Override
    public ResponseEntity<Admin> getProfile(String userid) {

        Admin admin = adminDao.findByUserid(userid);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(admin);
    }
    
}
