package com.hindra.fc.service.web.implementation;

import java.util.ArrayList;
import java.util.List;

import com.hindra.fc.dao.PaymentDao;
import com.hindra.fc.model.Payment;
import com.hindra.fc.service.web.TransactionService;
import com.hindra.fc.util.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public ResponseEntity<?> listTransactions() {
        Boolean status = false;
        String msg = "";
        List<Payment> data = new ArrayList<>();

        try {

            data = paymentDao.findAll();

            status = true;
            msg = "List of transactions.";
            
        } catch (Exception e) {
            msg = e.getMessage();
        }
        
        Response<List<Payment>> res = new Response<List<Payment>>(status, msg, data);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res);
    }
    
}
