package com.hindra.fc.service.web;

import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<?> listTransactions();
}
