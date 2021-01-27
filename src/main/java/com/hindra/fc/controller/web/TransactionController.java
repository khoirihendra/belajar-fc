package com.hindra.fc.controller.web;

import com.hindra.fc.service.web.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/web")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    @GetMapping(path = "/transactions", produces = "application/json")
    public ResponseEntity<?> listTransactions() {
        
        return transactionService.listTransactions();
    }
}
