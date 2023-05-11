package com.bedevenamdua.controllers;

import com.bedevenamdua.dto.TransactionData;
import com.bedevenamdua.entity.Business;
import com.bedevenamdua.entity.Transaction;
import com.bedevenamdua.service.BusinessService;
import com.bedevenamdua.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final BusinessService businessService;

    @GetMapping()
    public ResponseEntity<Iterable<Transaction>> findAllTransactions() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findTransactionById(@PathVariable int id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Transaction with id " + id + " is not found"));
        }
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/businesses/{businessId}")
    public ResponseEntity<Object> findTransactionsByBusinessId(@PathVariable UUID businessId) {
        Business business = businessService.getBusinessById(businessId);
        if (business == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Business id is not found"));
        }
        return ResponseEntity.ok(transactionService.findByBusinessId(businessId));
    }

    @PostMapping
    public ResponseEntity<Object> addTransactionByBusinessId(@Validated @RequestBody TransactionData request) {
        Business business = businessService.getBusinessById(request.getBusinessId());
        if (business == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Can't add transaction, because business ID that you input is not found"));
        }
        return ResponseEntity.ok(transactionService.addTransaction(request.getType(), request.getBusinessId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTransactionById(@PathVariable int id, @Validated @RequestBody TransactionData request) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Transaction with id " + id + " is not found"));
        }
        return ResponseEntity.ok(transactionService.updateTransaction(id, request.getType()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTransactionById(@PathVariable int id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Transaction with id " + id + " is not found"));
        }
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok(Map.of("message", "Transaction with id " + id + " has been deleted"));
    }
}
