package com.hindra.fc.service.mobile.implementation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.hindra.fc.dao.LoginDao;
import com.hindra.fc.dao.UserDao;
import com.hindra.fc.model.Login;
import com.hindra.fc.model.User;
import com.hindra.fc.service.mobile.UserServiceMobile;
import com.hindra.fc.util.JwtTokenUtil;
import com.hindra.fc.util.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceMobileImpl implements UserServiceMobile {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil jwt;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<?> register(Login login) {
        Boolean status = false;
        String msg = "";
        Map<String, String> data = new HashMap<String, String>();

        try {
            String uuid = UUID.randomUUID().toString();

            // generate token
            String token = jwt.generateToken(login);

            // add credential
            login.setUserid(uuid);
            login.setPassword(bCryptPasswordEncoder.encode(login.getPassword()));
            login.setToken(token);
            login.setCreatedat(new Date());
            login.setCreatedby(uuid);
            loginDao.save(login);

            // add admin detail
            User user = new User();
            user.setUserid(uuid);
            user.setFullname(login.getFullname());
            user.setCreatedat(new Date());
            user.setCreatedby(uuid);
            userDao.save(user);

            data.put("token", token);

            status = true;
            msg = "Your account has been created.";

        } catch (Exception e) {
            msg = e.getMessage();
        }

        Response<Map<?, ?>> res = new Response<Map<?, ?>>(status, msg, data);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }

    @Override
    public ResponseEntity<?> login(Login login) {

        Boolean status = false;
        String msg = "";
        Map<String, String> data = new HashMap<String, String>();

        try {

            // find user
            Login user = loginDao.findByUsername(login.getUsername());

            if (user == null) {
                throw new Exception("Wrong username or password.");
            }

            // compare password
            Boolean auth = bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword());

            if (!auth) {
                throw new Exception("Wrong username or password.");
            }

            // generate token
            String token = jwt.generateToken(user);

            // update last login
            user.setToken(token);
            user.setUpdatedat(new Date());
            user.setUpdatedby(user.getUserid());
            loginDao.save(user);

            data.put("token", token);

            status = true;
            msg = "Login success.";

        } catch (Exception e) {
            msg = e.getMessage();
        }

        Response<Map<?, ?>> res = new Response<Map<?, ?>>(status, msg, data);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }

    @Override
    public ResponseEntity<?> getProfile(String token) {

        Boolean status = false;
        String msg = "";
        User data = new User();

        try {
            String userid = jwt.getUserIdFromToken(token);
            data = userDao.findByUserid(userid);

            if(data == null) {
                throw new Exception("User doesn't exist.");
            }

            status = true;
            msg = "User detail.";
            
        } catch (Exception e) {
            msg = e.getMessage();
        }
        
        Response<User> res = new Response<User>(status, msg, data);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }
    
}
