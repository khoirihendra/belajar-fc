package com.hindra.fc.service.web.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hindra.fc.dao.AdminDao;
import com.hindra.fc.dao.LoginDao;
import com.hindra.fc.dao.UserDao;
import com.hindra.fc.dto.GoogleProfileDTO;
import com.hindra.fc.model.Admin;
import com.hindra.fc.model.Login;
import com.hindra.fc.model.User;
import com.hindra.fc.service.web.UserService;
import com.hindra.fc.util.GoogleHelper;
import com.hindra.fc.util.JwtTokenUtil;
import com.hindra.fc.util.Response;

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
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil jwt;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<?> register(Login login) {
        Boolean status = false;
        String msg = "";
        Map<String, Object> data = new HashMap<String, Object>();

        try {
            String uuid = UUID.randomUUID().toString();
            login.setUserid(uuid);

            // generate token
            String token = jwt.generateToken(login);

            // add credential
            login.setPassword(bCryptPasswordEncoder.encode(login.getPassword()));
            login.setToken(token);
            login.setCreatedat(new Date());
            login.setCreatedby(uuid);
            loginDao.save(login);

            // add admin detail
            Admin admin = new Admin();
            admin.setUserid(uuid);
            admin.setFullname(login.getFullname());
            admin.setCreatedat(new Date());
            admin.setCreatedby(uuid);
            adminDao.save(admin);

            data.put("token", token);
            data.put("user", admin);

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
        Map<String, Object> data = new HashMap<String, Object>();

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
            data.put("user", adminDao.findByUserid(user.getUserid()));

            status = true;
            msg = "Login success.";

        } catch (Exception e) {
            msg = e.getMessage();
        }

        Response<Map<?, ?>> res = new Response<Map<?, ?>>(status, msg, data);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }

    @Override
    public ResponseEntity<?> loginGoogle(GoogleProfileDTO googleAccessToken) {
        Boolean status = false;
        String msg = "";
        Map<String, Object> data = new HashMap<String, Object>();

        try {
            //validate google access token and get google profile
            String googleProfileJson = GoogleHelper.getUserInfo(googleAccessToken.getGoogleAccessTOken());

            if(googleProfileJson == null || googleProfileJson == "") {
                throw new Exception("Invalid access token");
            } 

            ObjectMapper objectMapper = new ObjectMapper();
            GoogleProfileDTO googleProfile = objectMapper.readValue(googleProfileJson, GoogleProfileDTO.class);

            String email = googleProfile.getEmail();

            // check whether user exists
            Login user = loginDao.findByEmail(email);

            if (user == null) {
                status = true;
                msg = "Go to registration page.";

                data.put("user", new User());
            }
            else {
                // generate token
                String token = jwt.generateToken(user);

                // update last login
                user.setToken(token);
                user.setUpdatedat(new Date());
                user.setUpdatedby(user.getUserid());
                loginDao.save(user);

                data.put("token", token);
                data.put("user", adminDao.findByUserid(user.getUserid()));

                status = true;
                msg = "Login success.";
            }

            
        } catch (Exception e) {
            msg = e.getMessage();
        }

        Response<Map<?, ?>> res = new Response<Map<?, ?>>(status, msg, data);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }

    @Override
    public ResponseEntity<?> getProfile(String userid) {

        Boolean status = false;
        String msg = "";
        Admin data = new Admin();

        try {

            data = adminDao.findByUserid(userid);

            if(data == null) {
                throw new Exception("User doesn't exist.");
            }

            status = true;
            msg = "User detail.";
            
        } catch (Exception e) {
            msg = e.getMessage();
        }
        
        Response<Admin> res = new Response<Admin>(status, msg, data);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }

    @Override
    public ResponseEntity<?> listMobileUsers() {
        
        Boolean status = false;
        String msg = "";
        List<User> data = new ArrayList<>();

        try {

            data = userDao.findAll();

            status = true;
            msg = "List of mobile users.";
            
        } catch (Exception e) {
            msg = e.getMessage();
        }
        
        Response<List<User>> res = new Response<List<User>>(status, msg, data);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }

}
